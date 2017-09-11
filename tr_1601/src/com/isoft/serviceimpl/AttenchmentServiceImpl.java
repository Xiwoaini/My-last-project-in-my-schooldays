package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoft.dao.TattenchmentDao;
import com.isoft.model.Tattenchment;
import com.isoft.service.AttenchmentService;

@Service
public class AttenchmentServiceImpl implements AttenchmentService {
	
	@Resource
	private TattenchmentDao attDao;
	
	/**
	 * 通过供应商的ID去查询对应的附件信息
	 */
	@Override
	public List<Tattenchment> loadShowFile(String tAgencyId) {
		return attDao.loadShowFile(tAgencyId);
	}

	/**
	 * 向上传文件后向附件表中插入一条信息
	 */
	@Override
	public int insertSelective(Tattenchment tatt) {
		return attDao.insertSelective(tatt);
	}

	/**
	 * 根据id删除文件
	 * @param tAttid
	 * @return
	 */
	@Override
	public boolean deleteFile(String tAttid) {
		return attDao.deleteByPrimaryKey(tAttid)>0?true:false;
	}

	/**
	 * 通过附件的ID查询附件的信息
	 */
	@Override
	public Tattenchment selectFile(String tAttid) {
		return attDao.selectByPrimaryKey(tAttid);
	}

}
