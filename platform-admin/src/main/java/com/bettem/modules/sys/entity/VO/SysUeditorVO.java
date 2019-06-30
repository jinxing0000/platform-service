package com.bettem.modules.sys.entity.VO;

import java.io.Serializable;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: bettem-security
 * @CreateDate: Created in 2018/12/20 14:22 <br>
 * @Author: 颜金星
 */
public class SysUeditorVO implements Serializable {

    private static final long serialVersionUID = 1L;
    //上传成功状态
    private String state;
    //文件路径
    private String url;
    //文件标题
    private String title;
    //源文件名称
    private String original;
    //文件大小
    private long size;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
