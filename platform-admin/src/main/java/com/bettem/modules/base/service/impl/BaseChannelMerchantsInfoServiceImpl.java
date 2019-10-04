package com.bettem.modules.base.service.impl;

import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.VO.SysUserVO;
import com.bettem.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.bettem.modules.base.dao.BaseChannelMerchantsInfoDao;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("baseChannelMerchantsInfoService")
public class BaseChannelMerchantsInfoServiceImpl extends ServiceImpl<BaseChannelMerchantsInfoDao, BaseChannelMerchantsInfoEntity> implements BaseChannelMerchantsInfoService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseChannelMerchantsInfoEntity> page = this.selectPage(
                new Query<BaseChannelMerchantsInfoEntity>(params).getPage(),
                new EntityWrapper<BaseChannelMerchantsInfoEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<BaseChannelMerchantsInfoEntity> BaseChannelMerchantsInfoList=new ArrayList<>();
        BaseChannelMerchantsInfoEntity baseChannelMerchantsInfoEntity=null;
        for(int i=0;i<ids.length;i++){
            baseChannelMerchantsInfoEntity=new BaseChannelMerchantsInfoEntity();
            baseChannelMerchantsInfoEntity.setId(ids[i]);
            baseChannelMerchantsInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseChannelMerchantsInfoEntity.setModifyDate(new Date());
            baseChannelMerchantsInfoEntity.setDeleteState(Constant.DELETE_STATE_YES);
            BaseChannelMerchantsInfoList.add(baseChannelMerchantsInfoEntity);
        }
        this.updateBatchById(BaseChannelMerchantsInfoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveChannelMerchantsInfo(BaseChannelMerchantsInfoEntity channelMerchantsInfoEntity) {
        String openId=channelMerchantsInfoEntity.getOpenId();
        String mobile=channelMerchantsInfoEntity.getContactNumber();
        BaseChannelMerchantsInfoEntity channelMerchantsInfo=this.selectById(openId);
        if(channelMerchantsInfo!=null){
            throw new RRException(ErrorCodeConstant.ERROR,"您已经完成注册，请勿重复提交！！");
        }
        Map<String,Boolean> checkUser=sysUserService.checkUserNameOrMobileExistence(mobile,mobile);
        if(!checkUser.get("userName")){
            throw new RRException(ErrorCodeConstant.ERROR,"该手机号已被注册，请更换手机号！！");
        }
        channelMerchantsInfoEntity.setId(openId);
        channelMerchantsInfoEntity.setLevel("1");
        channelMerchantsInfoEntity.setState("2");
        channelMerchantsInfoEntity.setCreateDate(new Date());
        channelMerchantsInfoEntity.setDeleteState(Constant.DELETE_STATE_NO);
        this.insert(channelMerchantsInfoEntity);
        SysUserVO userVO=new SysUserVO();
        userVO.setUserId(openId);
        userVO.setUsername(mobile);
        userVO.setNickName(channelMerchantsInfoEntity.getChannelName());
        userVO.setMobile(mobile);
        userVO.setStatus(Constant.USER_STATE_YES);
        userVO.setCreateTime(new Date());
        //默认密码为123456
        userVO.setPassword("123456");
        SysRoleEntity role=new SysRoleEntity();
        role.setRoleId(Constant.CHANNEL_MERCHANTS_ROLE_ID);
        List<SysRoleEntity> roleList=new ArrayList<>();
        roleList.add(role);
        userVO.setRoleIdList(roleList);
        sysUserService.save(userVO);
    }

}
