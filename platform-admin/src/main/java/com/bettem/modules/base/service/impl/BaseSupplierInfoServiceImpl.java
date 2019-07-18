package com.bettem.modules.base.service.impl;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.entity.VO.SysUserVO;
import com.bettem.modules.sys.service.SysUserService;
import com.bettem.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.Query;

import com.bettem.modules.base.dao.BaseSupplierInfoDao;
import com.bettem.modules.base.entity.BaseSupplierInfoEntity;
import com.bettem.modules.base.service.BaseSupplierInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("baseSupplierInfoService")
public class BaseSupplierInfoServiceImpl extends ServiceImpl<BaseSupplierInfoDao, BaseSupplierInfoEntity> implements BaseSupplierInfoService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Autowired
    private SysUserService sysUserService;

    /** 生成主键策略 */
    public String createId() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String supplierName=(String)params.get("supplierName");
        String contactsName=(String)params.get("contactsName");
        String contactNumber=(String)params.get("contactNumber");
        Page<BaseSupplierInfoEntity> page = this.selectPage(
                new Query<BaseSupplierInfoEntity>(params).getPage(),
                new EntityWrapper<BaseSupplierInfoEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
                .like(supplierName!=null,"supplier_name",supplierName)
                .like(contactsName!=null,"contacts_name",contactsName)
                .like(contactNumber!=null,"contact_number",contactNumber)
                .orderBy("create_date desc")
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<BaseSupplierInfoEntity> BaseSupplierInfoList=new ArrayList<>();
        BaseSupplierInfoEntity baseSupplierInfoEntity=null;
        for(int i=0;i<ids.length;i++){
            baseSupplierInfoEntity=new BaseSupplierInfoEntity();
            baseSupplierInfoEntity.setId(ids[i]);
            baseSupplierInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseSupplierInfoEntity.setModifyDate(new Date());
            baseSupplierInfoEntity.setDeleteState(Constant.DELETE_STATE_YES);
            BaseSupplierInfoList.add(baseSupplierInfoEntity);
        }
        this.deleteBatchIds(Arrays.asList(ids));
        sysUserService.deleteUserByUserIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBaseSupplierInfo(BaseSupplierInfoEntity supplierInfoEntity) {
        String id=createId();
        supplierInfoEntity.setId(id);
        supplierInfoEntity.setState(Constant.USER_STATE_YES);
        supplierInfoEntity.setCreateDate(new Date());
        supplierInfoEntity.setCreateUserId(shiroTokenUtils.getUserId());
        supplierInfoEntity.setDeleteState(Constant.DELETE_STATE_NO);
        this.insert(supplierInfoEntity);
        SysUserVO sysUserVO=new SysUserVO();
        sysUserVO.setUserId(id);
        String mobile=supplierInfoEntity.getContactNumber();
        sysUserVO.setUsername(mobile);
        sysUserVO.setPassword(mobile);
        sysUserVO.setMobile(mobile);
        sysUserVO.setCreateTime(new Date());
        sysUserVO.setNickName(supplierInfoEntity.getSupplierName());
        sysUserVO.setStatus(Constant.USER_STATE_YES);
        List<SysRoleEntity> roleList=new ArrayList<>();
        SysRoleEntity role=new SysRoleEntity();
        role.setRoleId(Constant.SUPPLIER_ROLE_ID);
        roleList.add(role);
        sysUserVO.setRoleIdList(roleList);
        sysUserService.save(sysUserVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disabling(String[] ids) {
        List<BaseSupplierInfoEntity> baseSupplierInfoList=new ArrayList<>();
        List<SysUserEntity> userList=new ArrayList<>();
        BaseSupplierInfoEntity baseSupplierInfoEntity=null;
        SysUserEntity sysUserEntity=null;
        for(int i=0;i<ids.length;i++){
            baseSupplierInfoEntity=new BaseSupplierInfoEntity();
            sysUserEntity=new SysUserEntity();
            baseSupplierInfoEntity.setId(ids[i]);
            baseSupplierInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseSupplierInfoEntity.setModifyDate(new Date());
            baseSupplierInfoEntity.setState(Constant.USER_STATE_NO);
            baseSupplierInfoList.add(baseSupplierInfoEntity);
            sysUserEntity.setUserId(ids[i]);
            sysUserEntity.setStatus(Constant.USER_STATE_NO);
            userList.add(sysUserEntity);
        }
        this.updateBatchById(baseSupplierInfoList);
        sysUserService.updateBatchById(userList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enabling(String[] ids) {
        List<BaseSupplierInfoEntity> baseSupplierInfoList=new ArrayList<>();
        List<SysUserEntity> userList=new ArrayList<>();
        BaseSupplierInfoEntity baseSupplierInfoEntity=null;
        SysUserEntity sysUserEntity=null;
        for(int i=0;i<ids.length;i++){
            baseSupplierInfoEntity=new BaseSupplierInfoEntity();
            sysUserEntity=new SysUserEntity();
            baseSupplierInfoEntity.setId(ids[i]);
            baseSupplierInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseSupplierInfoEntity.setModifyDate(new Date());
            baseSupplierInfoEntity.setState(Constant.USER_STATE_YES);
            baseSupplierInfoList.add(baseSupplierInfoEntity);
            sysUserEntity.setUserId(ids[i]);
            sysUserEntity.setStatus(Constant.USER_STATE_YES);
            userList.add(sysUserEntity);
        }
        this.updateBatchById(baseSupplierInfoList);
        sysUserService.updateBatchById(userList);
    }

}
