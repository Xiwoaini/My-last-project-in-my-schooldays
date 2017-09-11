package com.isoft.service;


import com.isoft.model.PageBean;
import com.isoft.model.TdicCode;
/**
 * 数据字典
 * @author Derek
 *
 */
public interface DicListService {
	//获取数据字典的数据类型
	public String getDicTypeSelect();
	
	//当页面加载时获取数据字典的所有数据
	//通过数据名称或数据类型获取数据字典的所有数据
	/*public List<TdicCode> getAllDicCode(TdicCode dic,PageBean page);*/
	
	//查询总条数
	/*public String selectCount();*/
	
	//添加数据字典
	public boolean addTDicCode(TdicCode dic);
	
	//根据id更新数据字典
	public boolean updateTDicCode(TdicCode dic);
	
	//根据id查询数据字典
	public TdicCode selectByPrimaryKey(String tDicId);
	
	//根据id删除数据字典
	public boolean deleteTDicCode(String tDicId);

	public PageBean<TdicCode> getAllDicCode2(String tName, String tType, int currPage, int pageSize);
	
	
	//获取数据字典的数据类型2
	public String getDicType2(TdicCode code);
	//获取数据字典的数据类型3
	public String getDicType3(TdicCode code); 
	
}
