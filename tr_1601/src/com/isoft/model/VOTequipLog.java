package com.isoft.model;

public class VOTequipLog {
    private String tLogId;
    private String tPresonId;
    private String tEquipId;
    //维护日期
    private String tLogAddtime;
    //维护日志记录
    private String tFixContent;
    //维护人员
    private String tCname;
    //设备名称
	private String t_device_name;
	public String gettLogId() {
		return tLogId;
	}
	public void settLogId(String tLogId) {
		this.tLogId = tLogId;
	}
	public String gettPresonId() {
		return tPresonId;
	}
	public void settPresonId(String tPresonId) {
		this.tPresonId = tPresonId;
	}
	public String gettEquipId() {
		return tEquipId;
	}
	public void settEquipId(String tEquipId) {
		this.tEquipId = tEquipId;
	}
	public String gettLogAddtime() {
		return tLogAddtime;
	}
	public void settLogAddtime(String tLogAddtime) {
		this.tLogAddtime = tLogAddtime;
	}
	public String gettFixContent() {
		return tFixContent;
	}
	public void settFixContent(String tFixContent) {
		this.tFixContent = tFixContent;
	}
	public String gettCname() {
		return tCname;
	}
	public void settCname(String tCname) {
		this.tCname = tCname;
	}
	public String getT_device_name() {
		return t_device_name;
	}
	public void setT_device_name(String t_device_name) {
		this.t_device_name = t_device_name;
	}
}
