package com.isoft.dao;

import java.util.List;

import com.isoft.model.Tattenchment;

public interface TattenchmentDao {
	List<Tattenchment> getAllAttByTid(String tid);
    
	int deleteByPrimaryKey(String tAttid);

    int insert(Tattenchment record);

    int insertSelective(Tattenchment record);

    Tattenchment selectByPrimaryKey(String tAttid);

    int updateByPrimaryKeySelective(Tattenchment record);

    int updateByPrimaryKey(Tattenchment record);
    
    //通过供应商的ID去查询对应的附件信息
	List<Tattenchment> loadShowFile(String tAgencyId);
  
}