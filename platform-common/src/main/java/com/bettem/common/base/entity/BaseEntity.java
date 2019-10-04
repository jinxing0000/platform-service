package com.bettem.common.base.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: bettem-security
 * @CreateDate: Created in 2018/10/9 09:06 <br>
 * @Author: 颜金星
 */
@JsonIgnoreProperties(value={"createDate","createUserId","modifyUserId","modifyDate","deleteState"})
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //实体主键id
    @TableId
    private String id;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //创建人
    private String createUserId;
    //修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
    //修改人
    private String modifyUserId;
    //删除状态
    private String deleteState;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getDeleteState() {
        return deleteState;
    }
}
