package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoft.dao.TequipLogDao;
import com.isoft.model.PageBean;
import com.isoft.model.TequipLog;
import com.isoft.model.VOTequipLog;
import com.isoft.service.EquipLogService;

@Service
public class EquipLogServiceImpl implements EquipLogService{
	@Resource
	private TequipLogDao tequipLogDao;
	/**
	 * 加载设备维护日志所有数据
	 */
//	@Override
//	public List<VOTequipLog> getEquipLog(VOTequipLog voequi,PageBean page) {
//		return tequipLogDao.getEquipLog(voequi.gettCname(),voequi.getT_device_name(),
//				page.getNextStartPage(),page.getV_pageSize());
//	}
	/**
	 * 通过id查询数据显示到修改删除窗口
	 */
	@Override
	public VOTequipLog getEquipLogById(String tLogId) {
		return tequipLogDao.getEquipLogById(tLogId);
	}
	
	/**
	 * 添加和更新设备维护日志
	 */
	@Override
	public boolean addOrUpdateEquipLog(TequipLog equip) {
		return tequipLogDao.updateByPrimaryKeySelective(equip)>0?true:false;
	}
	
	/**
	 * 删除设备维护日志
	 */
	@Override
	public boolean delteEquipLog(String parameter) {
		return tequipLogDao.deleteByPrimaryKey(parameter)>0?true:false;
	}
	
	/**
	 * 查询数据总条数用于分页
	 */
//	@Override
//	public String selectCount() {
//		return tequipLogDao.selectCount();
//	}
	@Override
	public PageBean<VOTequipLog> getEquipLog(String tCname, String t_device_name,int currPage, int pageSize) {
		List<VOTequipLog> list = tequipLogDao.getEquipLog(tCname, t_device_name, currPage*pageSize, pageSize);
		
		int totalCount = Integer.parseInt(tequipLogDao.selectCount( tCname,  t_device_name));
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

}
