package com.isoft.model;

public class Tattenchment {
    private String tAttid;

    private String tId;

    private String tAttname;

    private String tType;

    private String tAtturl;

    public String gettAttid() {
        return tAttid;
    }

    public void settAttid(String tAttid) {
        this.tAttid = tAttid == null ? null : tAttid.trim();
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public String gettAttname() {
        return tAttname;
    }

    public void settAttname(String tAttname) {
        this.tAttname = tAttname == null ? null : tAttname.trim();
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType == null ? null : tType.trim();
    }

    public String gettAtturl() {
        return tAtturl;
    }

    public void settAtturl(String tAtturl) {
        this.tAtturl = tAtturl == null ? null : tAtturl.trim();
    }
}