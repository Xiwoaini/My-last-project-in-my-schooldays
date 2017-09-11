package com.isoft.model;

public class TequipLog {
    private String tLogId;

    private String tPresonId;

    private String tEquipId;

    private String tLogAddtime;

    private String tFixContent;

    public String gettLogId() {
        return tLogId;
    }

    public void settLogId(String tLogId) {
        this.tLogId = tLogId == null ? null : tLogId.trim();
    }

    public String gettPresonId() {
        return tPresonId;
    }

    public void settPresonId(String tPresonId) {
        this.tPresonId = tPresonId == null ? null : tPresonId.trim();
    }

    public String gettEquipId() {
        return tEquipId;
    }

    public void settEquipId(String tEquipId) {
        this.tEquipId = tEquipId == null ? null : tEquipId.trim();
    }

    public String gettLogAddtime() {
        return tLogAddtime;
    }

    public void settLogAddtime(String tLogAddtime) {
        this.tLogAddtime = tLogAddtime == null ? null : tLogAddtime.trim();
    }

    public String gettFixContent() {
        return tFixContent;
    }

    public void settFixContent(String tFixContent) {
        this.tFixContent = tFixContent == null ? null : tFixContent.trim();
    }
}