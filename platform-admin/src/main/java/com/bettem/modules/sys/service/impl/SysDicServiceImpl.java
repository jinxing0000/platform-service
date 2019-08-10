package com.bettem.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.sys.dao.SysDicDao;
import com.bettem.modules.sys.entity.SysDicEntity;
import com.bettem.modules.sys.service.SysDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("sysDicService")
public class SysDicServiceImpl extends ServiceImpl<SysDicDao, SysDicEntity> implements SysDicService {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(SysDicServiceImpl.class);

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String parentCode=(String)params.get("parentCode");
        String code=(String)params.get("code");
        String value=(String)params.get("value");
        if(parentCode==null){
            parentCode= Constant.SYS_DIC_PARENT_CODE_ROOT;
        }
        Page<SysDicEntity> page = this.selectPage(
                new Query<SysDicEntity>(params).getPage(),
                new EntityWrapper<SysDicEntity>()
                        .eq("delete_state", Constant.DELETE_STATE_NO)
                        .eq("parent_code",parentCode)
                        .eq(code!=null,"code",code)
                        .like(value!=null,"value",value)
                        .orderBy("sort")
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<SysDicEntity> SysDicList=new ArrayList<>();
        SysDicEntity sysDicEntity=null;
        for(int i=0;i<ids.length;i++){
            sysDicEntity=new SysDicEntity();
            sysDicEntity.setId(ids[i]);
            sysDicEntity.setModifyUserId(shiroTokenUtils.getUserId());
            sysDicEntity.setModifyDate(new Date());
            sysDicEntity.setDeleteState(Constant.DELETE_STATE_YES);
            SysDicList.add(sysDicEntity);
        }
        if(SysDicList.size()>0){
            List<SysDicEntity> list=this.baseMapper.selectSysDicByIds(ids);
            if(list.size()>0){
                SysDicEntity newSysDicEntity=list.get(0);
                //判断是否删除根目录
                if(Constant.SYS_DIC_PARENT_CODE_ROOT.equals(newSysDicEntity.getParentCode())){
                    for(SysDicEntity sysDic:list){
                        redisUtils.delete(RedisKeys.getSysDicDataKey(sysDic.getCode()));
                    }
                }
                this.updateBatchById(SysDicList);
                this.baseMapper.deleteSysDicByIds(ids);
                //判断不为根目录的数据
                if(!Constant.SYS_DIC_PARENT_CODE_ROOT.equals(newSysDicEntity.getParentCode())){
                    Map<String,Object> redisData=new LinkedHashMap<>();
                    List<SysDicEntity>  newSysDicList=this.selectList(new EntityWrapper<SysDicEntity>().
                            eq("delete_state", Constant.DELETE_STATE_NO)
                            .eq("parent_code",newSysDicEntity.getParentCode())
                            .eq("delete_state", Constant.DELETE_STATE_NO)
                            .orderBy("sort")
                    );
                    for(SysDicEntity sysDic:newSysDicList){
                        redisData.put(sysDic.getCode(),sysDic.getValue());
                    }
                    redisUtils.delete(RedisKeys.getSysDicDataKey(newSysDicEntity.getParentCode()));
                    redisUtils.setMap(RedisKeys.getSysDicDataKey(newSysDicEntity.getParentCode()),redisData);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysDic(SysDicEntity sysDic) {
        Map<String,Object> redisData=new LinkedHashMap<>();
        String parentCode=sysDic.getParentCode();
        if(parentCode==null){
            parentCode= Constant.SYS_DIC_PARENT_CODE_ROOT;
        }
        String code=sysDic.getCode();
        List<SysDicEntity> sysDicList=this.selectList(new EntityWrapper<SysDicEntity>().eq("delete_state", Constant.DELETE_STATE_NO).eq("parent_code",parentCode).eq("code",code));
        if(sysDicList.size()>0){
            throw new RRException(ErrorCodeConstant.ERROR,"数据字典code值重复！！");
        }
        sysDic.setParentCode(parentCode);
        sysDic.setCreateDate(new Date());
        sysDic.setCreateUserId(shiroTokenUtils.getUserId());
        sysDic.setDeleteState(Constant.DELETE_STATE_NO);
        this.insert(sysDic);
        String redisKeyCode="";
        //判断是否时添加的第一层数据，如果不是，执行该代码
        if(!Constant.SYS_DIC_PARENT_CODE_ROOT.equals(parentCode)){
            redisKeyCode=parentCode;
            List<SysDicEntity>  newSysDicList=this.selectList(new EntityWrapper<SysDicEntity>().
                eq("delete_state", Constant.DELETE_STATE_NO)
                .eq(parentCode!=null,"parent_code",parentCode)
                .eq("delete_state", Constant.DELETE_STATE_NO)
                .orderBy("sort")
            );
            for(SysDicEntity sysDicEntity:newSysDicList){
                redisData.put(sysDicEntity.getCode(),sysDicEntity.getValue());
            }
            redisUtils.delete(RedisKeys.getSysDicDataKey(redisKeyCode));
        }else {
            redisKeyCode=sysDic.getCode();
        }
        //将redis中数据更新
        redisUtils.setMap(RedisKeys.getSysDicDataKey(redisKeyCode),redisData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSysDic(SysDicEntity sysDic) {
        Map<String,Object> redisData=new LinkedHashMap<>();
        String parentCode=sysDic.getParentCode();
        if(parentCode==null){
            parentCode= Constant.SYS_DIC_PARENT_CODE_ROOT;
        }
        String code=sysDic.getCode();
        List<SysDicEntity> sysDicList=this.selectList(new EntityWrapper<SysDicEntity>().eq("delete_state", Constant.DELETE_STATE_NO).eq("parent_code",parentCode).eq("code",code));
        if(sysDicList.size()>1){
            throw new RRException(ErrorCodeConstant.ERROR,"数据字典code值重复！！");
        }
        //查询数量为1时，有可能是其他值，需要判断id
        else if(sysDicList.size()==1){
            String id=sysDicList.get(0).getId();
            //如果修改得数据id不一致，证明code重复
            if(!id.equals(sysDic.getId())){
                throw new RRException(ErrorCodeConstant.ERROR,"数据字典code值重复！！");
            }
        }
        sysDic.setModifyDate(new Date());
        sysDic.setModifyUserId(shiroTokenUtils.getUserId());
        this.updateById(sysDic);
        String redisKeyCode="";
        //判断是否时添加的第一层数据，如果不是，执行该代码
        if(!Constant.SYS_DIC_PARENT_CODE_ROOT.equals(parentCode)){
            redisKeyCode=parentCode;
        }else {
            redisKeyCode=sysDic.getCode();
        }
        List<SysDicEntity>  newSysDicList=this.selectList(new EntityWrapper<SysDicEntity>().
                eq("delete_state", Constant.DELETE_STATE_NO)
                .eq(parentCode!=null,"parent_code",parentCode)
                .eq("delete_state", Constant.DELETE_STATE_NO)
                .orderBy("sort")
        );
        for(SysDicEntity sysDicEntity:newSysDicList){
            redisData.put(sysDicEntity.getCode(),sysDicEntity.getValue());
        }
        redisUtils.delete(RedisKeys.getSysDicDataKey(redisKeyCode));
        //将redis中数据更新
        redisUtils.setMap(RedisKeys.getSysDicDataKey(redisKeyCode),redisData);
    }

    @Override
    public List<Map<String,Object>> findListByParams(Map<String, Object> params) {
        String parentCode=(String)params.get("parentCode");
        Map<String,Object> resultMap=redisUtils.getMap(RedisKeys.getSysDicDataKey(parentCode));
        List<Map<String,Object>> mapList=new ArrayList<>();
        Map<String,Object> map=null;
        for(Map.Entry<String, Object> entry : resultMap.entrySet()){
            map=new HashMap<>();
            String mapKey = entry.getKey();
            String mapValue = (String)entry.getValue();
            map.put("code",mapKey);
            map.put("value",mapValue);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void findSysDicDataToRedis() {
        List<SysDicEntity>  sysDicList=this.selectList(new EntityWrapper<SysDicEntity>().
                eq("delete_state", Constant.DELETE_STATE_NO)
                .orderBy("sort")
        );
        //循环获取数据
        for(SysDicEntity sysDicEntity:sysDicList){
            String parentCode=sysDicEntity.getParentCode();
            //寻找根节点
            if(Constant.SYS_DIC_PARENT_CODE_ROOT.equals(parentCode)){
                Map<String,Object> redisMap=this.setChildrenMap(sysDicEntity.getCode(),sysDicList);
                redisUtils.setMap(RedisKeys.getSysDicDataKey(sysDicEntity.getCode()),redisMap);
            }
        }
    }

    /**
     * 寻找下级节点
     * @param code
     * @param sysDicList
     * @return
     */
    public Map<String,Object> setChildrenMap(String code, List<SysDicEntity> sysDicList){
        Map<String,Object> childrenMap=new HashMap<>();
        /**
         *  循环数据字典表
         */
        for(SysDicEntity sysDic:sysDicList){
            //有下级子节点
            if(code.equals(sysDic.getParentCode())){
                childrenMap.put(sysDic.getCode(),sysDic.getValue());
            }
        }
        return childrenMap;
    }

}
