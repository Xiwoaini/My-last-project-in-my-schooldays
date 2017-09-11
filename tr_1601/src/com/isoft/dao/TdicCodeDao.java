package com.isoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoft.model.TdicCode;

public interface TdicCodeDao {
	
	//查询数据字典的数据类型
	List<TdicCode> getDicType();
	
	//当页面加载时获取数据字典的所有数据
	//通过数据名称或数据类型获取数据字典的所有数据
//	List<TdicCode> getAllDicCode(TdicCode dic);
	List<TdicCode> getAllDicCode(@Param("tName")String tName,@Param("tType")String tType,
			@Param("nextStartPage")int nextStartPage,@Param("pageSize")int pageSize);
	
	//查询总条数
	String selectCount(@Param("tName")String tName,@Param("tType")String tType);
	
	//添加数据字典
	int addTDicCode(TdicCode dic);
	
	//根据id更新数据字典
	int updateByPrimaryKeySelective(TdicCode dic);

	//根据id查询数据字典
	TdicCode selectByPrimaryKey(String tDicId);
	
	
	int deleteByPrimaryKey(String tDicId);

    int insert(TdicCode record);

    int insertSelective(TdicCode record);

    int updateByPrimaryKey(TdicCode record);
    //查询数据字典的数据类型2
    List<TdicCode> getDicType2(TdicCode code);
    //查询数据字典的数据类型3
    List<TdicCode> getDicType3(TdicCode code);

	


}