package com.isoft.service;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.isoft.model.Device;
import com.isoft.model.Tagency;
import com.isoft.model.TdicCode;
 
/**
 * @author f
 * @描述 设备管理业务接口
 * */
public interface DeviceService {
	//管理展现
	List<Device> selectDeviceAll(HttpServletRequest request)throws NumberFormatException, ParseException;
	//添加
	String addDevice(Device device,String t_agency_name)throws ParseException;
	//通过excel导入添加
	boolean addDeviceExcel(Device device);
	//删除
	boolean deleteDevice(String did);
	//修改
	Device updateDevice(String t_equip_id);
	//确认修改
	boolean updateDeviceTrue(Device device);
	//模糊查询
	String selectDeviceLike(HttpServletRequest request)throws NumberFormatException, ParseException;
//	导出
	public HSSFWorkbook exportDevice(List<Device> device);
	//设备详情
	public String selectDeviceDetail(String t_agency_id,String t_equip_id);
	//查询所有科室
	public List<Tagency> selectTagencyAll();
	//解析入的excel
	 public  List<List<Object>> getDeviceListByExcel(InputStream in,String fileName) throws Exception;
	//查询所有科室
	 public List<TdicCode> selecTdicCodesAll();
	 //设备检修查看检修人
	 String selectCheckPerson(String dic_id);
	 //确认检修
	 public String checkDeviceTrue(HttpServletRequest request);
	 //导出查询
	 public List<Device> excelExport();
	 
}
