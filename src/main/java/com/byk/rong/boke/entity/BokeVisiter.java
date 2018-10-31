package com.byk.rong.boke.entity;

import java.util.Date;

public class BokeVisiter {
    private String id;

    private String visiterIp;

    private Date createTime;

    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVisiterIp() {
        return visiterIp;
    }

    public void setVisiterIp(String visiterIp) {
        this.visiterIp = visiterIp == null ? null : visiterIp.trim();
    }

    public Date getTime() {
        return createTime;
    }

    public void setTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}