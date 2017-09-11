package com.isoft.model;

public class Tperson {
    private String tPresonId;

    private String tUserId;

    private String tDicId;

    private String tGender;

    private String tMobile;

    private String tEmail;

    private String tCname;

    public String gettPresonId() {
        return tPresonId;
    }

    public void settPresonId(String tPresonId) {
        this.tPresonId = tPresonId == null ? null : tPresonId.trim();
    }

    public String gettUserId() {
        return tUserId;
    }

    public void settUserId(String tUserId) {
        this.tUserId = tUserId == null ? null : tUserId.trim();
    }

    public String gettDicId() {
        return tDicId;
    }

    public void settDicId(String tDicId) {
        this.tDicId = tDicId == null ? null : tDicId.trim();
    }

    public String gettGender() {
        return tGender;
    }

    public void settGender(String tGender) {
        this.tGender = tGender == null ? null : tGender.trim();
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile == null ? null : tMobile.trim();
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail == null ? null : tEmail.trim();
    }

    public String gettCname() {
        return tCname;
    }

    public void settCname(String tCname) {
        this.tCname = tCname == null ? null : tCname.trim();
    }
}