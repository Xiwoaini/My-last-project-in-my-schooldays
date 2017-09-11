package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.isoft.dao.TattenchmentDao;
import com.isoft.model.Tattenchment;
import com.isoft.service.IUploadService;
@Service
public class UploadServiceIml implements IUploadService {
@Resource
private TattenchmentDao tattDao;
	@Override
	public boolean uploadFile(Tattenchment att) {
		// TODO Auto-generated method stub
		return tattDao.insertSelective(att)>0?true:false;
	}

	@Override
	public boolean updateFile(Tattenchment att) {
		// TODO Auto-generated method stub
		return  tattDao.updateByPrimaryKeySelective(att)>0?true:false;
	}

	@Override
	public boolean deleteFile(Tattenchment att) {
		// TODO Auto-generated method stub
		return tattDao.deleteByPrimaryKey(att.gettAttid())>0?true:false;
	}

	@Override
	public boolean checkFile(String tid) {
		// TODO Auto-generated method stub
	   List temp=tattDao.getAllAttByTid(tid);
	   return temp.size()>0?true:false;
	}

	@Override
	public String getAllAttJSON(String tid) {
		// TODO Auto-generated method stub
		return new Gson().toJson(tattDao.getAllAttByTid(tid));
	}

}
