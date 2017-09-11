package com.isoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoft.model.TequipLog;
import com.isoft.model.VOTequipLog;

public interface TequipLogDao {
	
	//加载设备维护日志所有数据
	List<VOTequipLog> getEquipLog(@Param("tCname")String tCname,@Param("t_device_name")String t_device_name,
			@Param("nextStartPage")int nextStartPage,@Param("pageSize")int pageSize);
	//通过id查询数据显示到修改删除窗口
	VOTequipLog getEquipLogById(String tLogId);
	//添加和更新设备维护日志
	int updateByPrimaryKeySelective(TequipLog record);
	
	TequipLog selectByPrimaryKey(String tLogId);
	
    int deleteByPrimaryKey(String tLogId);

    int insert(TequipLog record);

    int insertSelective(TequipLog record);

    int updateByPrimaryKey(TequipLog record);
    //查询数据总条数用于分页
	String selectCount(@Param("tCname")String tCname,@Param("t_device_name")String t_device_name);

}