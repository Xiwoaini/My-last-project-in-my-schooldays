package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.isoft.dao.TdicCodeDao;
import com.isoft.model.PageBean;
import com.isoft.model.TdicCode;
import com.isoft.service.DicListService;

@Service
public class DicListServiceimpl implements DicListService {
	@Resource
	private TdicCodeDao dicCodeDao;
	/**
	 * 查询数据字典的数据类型
	 */
	@Override
	public String getDicTypeSelect() {
		return new Gson().toJson(dicCodeDao.getDicType());
	}
	/**
	 * 当页面加载时获取数据字典的所有数据
	 * 通过数据名称或数据类型获取数据字典的所有数据
	 */
/*	@Override
	public List<TdicCode> getAllDicCode(TdicCode dic,PageBean page) {
		return dicCodeDao.getAllDicCode(dic.gettName(),dic.gettType(),page.getNextStartPage(),page.getV_pageSize());
	}*/
	/**
	 * 添加数据字典
	 */
	@Override
	public boolean addTDicCode(TdicCode dic) {
		return dicCodeDao.addTDicCode(dic)>0?true:false;
	}
	
	/**
	 * 根据id更新数据字典
	 */
	@Override
	public boolean updateTDicCode(TdicCode dic) {
		return dicCodeDao.updateByPrimaryKeySelective(dic)>0?true:false;
	}
	
	/**
	 * 根据id查询数据字典
	 */
	@Override
	public TdicCode selectByPrimaryKey(String tDicId) {
		return dicCodeDao.selectByPrimaryKey(tDicId);
	}
	
	/**
	 * 根据id删除数据字典
	 */
	@Override
	public boolean deleteTDicCode(String tDicId) {
		return dicCodeDao.deleteByPrimaryKey(tDicId)>0?true:false;
	}
	
	/**
	 * 查询总条数
	 */
/*	@Override
	public String selectCount() {
		return dicCodeDao.selectCount();
	}*/
	
	@Override
	public PageBean<TdicCode> getAllDicCode2(String tName, String tType, int currPage, int pageSize) {
		List<TdicCode> list=dicCodeDao.getAllDicCode(tName, tType, currPage*pageSize, pageSize);
		
		int totalCount=Integer.parseInt(dicCodeDao.selectCount(tName, tType));
		
		return  new PageBean<>(list, currPage, pageSize, totalCount);
	}
	
	/**
	 * 查询数据字典的数据类型2
	 */
	@Override
	public String getDicType2(TdicCode code) {
		// TODO Auto-generated method stub
		String str=new Gson().toJson(dicCodeDao.getDicType2(code));
		return str;
	}
	
	/**
	 * 查询数据字典的数据类型3
	 */
	@Override
	public String getDicType3(TdicCode code) {
		// TODO Auto-generated method stub
		String str=new Gson().toJson(dicCodeDao.getDicType3(code));
		return str;
	}
	

}
