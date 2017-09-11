package com.isoft.service;


import com.isoft.model.PageBean;
import com.isoft.model.Tagency;

public interface AgencyService {

	//加载和查询供应商页面数据
//	public List<Tagency> getAgency(String tAgencyName, PageBean page);

	//查询数据总条数用于分页
//	public String selectCount();
	
	//根据id查询当前设备信息数据
	public Tagency openAgencyUpdateWin(String tAgencyId);
	
	//添加供应商信息
	public boolean addAgency(Tagency tagency);
	//更新供应商信息
	public boolean updateAgency(Tagency tagency);
	//删除供应商信息
	public boolean delAgency(String tAgencyId);

	public PageBean<Tagency> getAgency(String tAgencyName, int currPage, int pageSize);
	
	
}
