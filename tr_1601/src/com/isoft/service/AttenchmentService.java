package com.isoft.service;

import java.util.List;

import com.isoft.model.Tattenchment;

public interface AttenchmentService {
	
	/**
	 * 通过供应商的ID去查询对应的附件信息
	 * @param tAgencyId
	 * @return
	 */
	List<Tattenchment> loadShowFile(String tAgencyId);

	/**
	 * 向上传文件后向附件表中插入一条信息
	 * @param tatt
	 * @return
	 */
	int insertSelective(Tattenchment tatt);
	
	/**
	 * 根据id删除文件
	 * @param tAttid
	 * @return
	 */
	boolean deleteFile(String tAttid);

	//通过附件的ID查询附件的信息
	Tattenchment selectFile(String tAttid);


}
