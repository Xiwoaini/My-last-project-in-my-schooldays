package com.isoft.model;

public class TsysUSer {
    private String tUserId;

    private String tUserName;

    private String tUserPwd;

    private String tRole;

    private String tStatus;

    public String gettUserId() {
        return tUserId;
    }

    public void settUserId(String tUserId) {
        this.tUserId = tUserId == null ? null : tUserId.trim();
    }

    public String gettUserName() {
        return tUserName;
    }

    public void settUserName(String tUserName) {
        this.tUserName = tUserName == null ? null : tUserName.trim();
    }

    public String gettUserPwd() {
        return tUserPwd;
    }

    public void settUserPwd(String tUserPwd) {
        this.tUserPwd = tUserPwd == null ? null : tUserPwd.trim();
    }

    public String gettRole() {
        return tRole;
    }

    public void settRole(String tRole) {
        this.tRole = tRole == null ? null : tRole.trim();
    }

    public String gettStatus() {
        return tStatus;
    }

    public void settStatus(String tStatus) {
        this.tStatus = tStatus == null ? null : tStatus.trim();
    }
}