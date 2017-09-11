package com.isoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isoft.model.Tagency;
import com.isoft.model.Tattenchment;
import com.isoft.model.TdicCode;
import com.isoft.model.Device;

//设备持久层接口
@Repository
public interface DeviceDAO {
	
	//管理展现
	List<Device> selectDeviceAll(@Param(value = "startPos") Integer startPos,
			@Param(value = "pageSize") Integer pageSize);
	//添加
	int addDevice(Device device);
	//删除
	int deleteDevice(String did);
	//修改
	Device selectDevice(@Param(value="t_equip_id") String t_equip_id);
	int updateDevice(Device device);
	//模糊查询
	List<Device> selectDeviceLike(
			@Param(value = "startPos") Integer startPos,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value="dname") String dname,
			@Param(value="tdiccode_name") String tdiccode_name);
	//得到总数,以便分页
	int getCounts();
	//模糊查询总数
	String getLikeCounts(@Param(value="dname") String dname,
			@Param(value="tdiccode_name") String tdiccode_name);
	//设备详情
	Tagency selectDeviceDetail(@Param(value="t_agency_id") String t_agency_id);
	//查询所有科室
	List<Tagency> selectTagencyAll();
	//根据供应商的名称查询具体的id
	String selectIdByTagencyName(@Param(value="t_agency_name") String t_agency_name);
	//查询所有科室
	List<TdicCode> selectTdicCodeAll();
	//通过科室代码找科室id
	String selectCheckDicId(@Param(value="dic_id") String dic_id);
	 //设备检修查看检修人
	 String selectCheckPerson(@Param(value="t_name") String t_name);
	 //通过检修人和科室id查询检修人id
	 String selectCheckPersonByDicName(@Param(value="tCname") String tCname
			 ,@Param(value="t_dic_code_check") String t_dic_code_check);
	
	 //通过科室代码查找科室名字
	 String selectDicNameById(@Param(value="dic_code") String dic_code);
	//通过科室名字查找科室代码
		 String selectDicCodeByDicName(@Param(value="dic_name") String dic_name);
	 //导出excel
	 List<Device> excelExport();
	 //更新时间
	 int updateDeviceLeftDay(@Param(value="t_left_day") String t_left_day,
			 @Param(value="t_equip_id") String t_equip_id);
	//获取图片路径
	 Tattenchment getImgSrc(@Param(value="t_equip_id") String t_equip_id);
}
