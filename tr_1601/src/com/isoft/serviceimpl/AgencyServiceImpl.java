package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoft.dao.TagencyDao;
import com.isoft.model.PageBean;
import com.isoft.model.Tagency;
import com.isoft.service.AgencyService;

@Service("agencyService")
public class AgencyServiceImpl implements AgencyService {
	
	@Resource
	private TagencyDao agencyDao;
	
	/**
	 * 加载和查询供应商页面数据
	 */
//	@Override
//	public List<Tagency> getAgency(String tAgencyName, PageBean page) {
//		return agencyDao.getAgency(tAgencyName,page.getNextStartPage(),page.getV_pageSize());
//	}
	
	/**
	 * 查询数据总条数用于分页
	 */
//	@Override
//	public String selectCount() {
//		return agencyDao.selectCount();
//	}
	
	/**
	 * 根据id查询当前设备信息数据
	 */
	@Override
	public Tagency openAgencyUpdateWin(String tAgencyId) {
		return agencyDao.selectByPrimaryKey(tAgencyId);
	}

	/**
	 * 添加供应商信息
	 */
	@Override
	public boolean addAgency(Tagency tagency) {
		return agencyDao.insertSelective(tagency)>0?true:false;
	}
	
	/**
	 * 更新供应商信息
	 */
	@Override
	public boolean updateAgency(Tagency tagency) {
		return agencyDao.updateByPrimaryKeySelective(tagency)>0?true:false;
	}
	
	/**
	 * 删除供应商信息
	 */
	@Override
	public boolean delAgency(String tAgencyId) {
		return agencyDao.deleteByPrimaryKey(tAgencyId)>0?true:false;
	}

	@Override
	public PageBean<Tagency> getAgency(String tAgencyName, int currPage, int pageSize) {
		List<Tagency> list = agencyDao.getAgency(tAgencyName, currPage*pageSize, pageSize);
		
		int totalCount = Integer.parseInt(agencyDao.selectCount(tAgencyName));
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

}
