package com.isoft.service;


import com.isoft.model.PageBean;
import com.isoft.model.TequipLog;
import com.isoft.model.VOTequipLog;

public interface EquipLogService {
	
	//加载设备维护日志所有数据
//	public List<VOTequipLog> getEquipLog(VOTequipLog voequi,PageBean page);
	
	//通过id查询数据显示到修改删除窗口
	public VOTequipLog getEquipLogById(String tLogId);
	
	//添加和更新设备维护日志
	public boolean addOrUpdateEquipLog(TequipLog equip);
	
	//删除设备维护日志
	public boolean delteEquipLog(String parameter);

	//查询数据总条数用于分页
//	public String selectCount();

	public PageBean<VOTequipLog> getEquipLog(String tCname, String t_device_name, int currPage, int pageSize);
	
}
