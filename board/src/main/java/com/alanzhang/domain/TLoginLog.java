package com.alanzhang.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TLoginLog implements Serializable {
    private String loginLogId;

    private String userId;

    private String ip;

    private Date loginDatetime;

    private Byte loginStatus;

    private static final long serialVersionUID = 1L;

    public String getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public Byte getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
    }
}