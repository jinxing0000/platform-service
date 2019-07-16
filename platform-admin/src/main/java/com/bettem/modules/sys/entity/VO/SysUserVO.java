package com.bettem.modules.sys.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.bettem.modules.sys.entity.SysMenuEntity;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.SysUserEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户信息VO
 */
public class SysUserVO  extends SysUserEntity {
    /**
     * 角色ID列表
     */
    @TableField(exist=false)
    private List<SysRoleEntity> roleIdList;

    /**
     * 部门名称
     */
    @TableField(exist=false)
    private String deptName;

    /**
     *  权限set
     */
    @TableField(exist=false)
    private Set<String> permsSet=new HashSet<>();

    /**
     *   用户菜单数据
     */
    @TableField(exist=false)
    private List<SysMenuEntity> menuList=new ArrayList<>();

    public void setRoleIdList(List<SysRoleEntity> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<SysRoleEntity> getRoleIdList() {
        return roleIdList;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setPermsSet(Set<String> permsSet) {
        this.permsSet = permsSet;
    }

    public Set<String> getPermsSet() {
        return permsSet;
    }

    public void setMenuList(List<SysMenuEntity> menuList) {
        this.menuList = menuList;
    }

    public List<SysMenuEntity> getMenuList() {
        return menuList;
    }
}
