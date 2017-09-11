package com.isoft.model;

//设备模型类
public class Device {
//设备主键
	private String t_equip_id;
//	供应商ID
	private String t_agency_id;
//	科室代码
	private String t_dic_code;
//	设备名称
	private String t_device_name;
//	设备类型
	private String t_dtype;
	
	private String t_factory;
//	提前预警天数
	private String t_dnumber;
//	设备购买日期
	private String t_buy_date;
//	设备检修日期
	private String t_check_date;
	
	private String t_sid;
//	设备检修周期
	private String t_check_circle;
//	距离下次检测日期
	private String t_left_day;
	
	private TdicCode tdiccode;
	public TdicCode getTdiccode() {
		return tdiccode;
	}

	public void setTdiccode(TdicCode tdiccode) {
		this.tdiccode = tdiccode;
	}


	public String getT_equip_id() {
		return t_equip_id;
	}

	public void setT_equip_id(String t_equip_id) {
		this.t_equip_id = t_equip_id;
	}

	public String getT_agency_id() {
		return t_agency_id;
	}

	public void setT_agency_id(String t_agency_id) {
		this.t_agency_id = t_agency_id;
	}

	public String getT_dic_code() {
		return t_dic_code;
	}

	public void setT_dic_code(String t_dic_code) {
		this.t_dic_code = t_dic_code;
	}

	public String getT_device_name() {
		return t_device_name;
	}

	public void setT_device_name(String t_device_name) {
		this.t_device_name = t_device_name;
	}

	public String getT_dtype() {
		return t_dtype;
	}

	public void setT_dtype(String t_dtype) {
		this.t_dtype = t_dtype;
	}

	public String getT_factory() {
		return t_factory;
	}

	public void setT_factory(String t_factory) {
		this.t_factory = t_factory;
	}

	public String getT_dnumber() {
		return t_dnumber;
	}

	public void setT_dnumber(String t_dnumber) {
		this.t_dnumber = t_dnumber;
	}

	public String getT_buy_date() {
		return t_buy_date;
	}

	public void setT_buy_date(String t_buy_date) {
		this.t_buy_date = t_buy_date;
	}

	public String getT_check_date() {
		return t_check_date;
	}

	public void setT_check_date(String t_check_date) {
		this.t_check_date = t_check_date;
	}

	public String getT_sid() {
		return t_sid;
	}

	public void setT_sid(String t_sid) {
		this.t_sid = t_sid;
	}

	public String getT_check_circle() {
		return t_check_circle;
	}

	public void setT_check_circle(String t_check_circle) {
		this.t_check_circle = t_check_circle;
	}

	public String getT_left_day() {
		return t_left_day;
	}

	public void setT_left_day(String t_left_day) {
		this.t_left_day = t_left_day;
	}

}
