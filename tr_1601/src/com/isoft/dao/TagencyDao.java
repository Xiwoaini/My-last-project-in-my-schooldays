package com.isoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoft.model.Tagency;

public interface TagencyDao {
	//加载和查询供应商页面数据
	List<Tagency> getAgency(@Param("tAgencyName")String tAgencyName, @Param("nextStartPage")int nextStartPage, @Param("pageSize")int pageSize);

	int deleteByPrimaryKey(String tAgencyId);

    int insert(Tagency record);

    //添加供应商信息
    int insertSelective(Tagency record);

    Tagency selectByPrimaryKey(String tAgencyId);
    
    //更新供应商信息
    int updateByPrimaryKeySelective(Tagency record);

    int updateByPrimaryKey(Tagency record);
    //查询数据总条数用于分页
	String selectCount(@Param("tAgencyName")String tAgencyName);

}