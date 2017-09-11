package com.isoft.service;


import java.util.List;

import com.isoft.model.PageBean;
import com.isoft.model.TsysUSer;
import com.isoft.model.VOUserPerson;



public interface TsysuserService {
	  public  boolean selectByuserName(String name);
	   public   TsysUSer selectByuser(TsysUSer user);
	   public boolean insertSelective(TsysUSer record);
	   public List<TsysUSer> selDisticRole();
	     public  boolean  selUserByPwd(TsysUSer user);
	   public  boolean   updatePassword(TsysUSer user);
	   public  TsysUSer asUserRole(String userId);
	   
//	  public String selectUserPerson(String username,Page1 paper);
	  
	  public boolean updateStatus(TsysUSer user);
	  public boolean deleteByPrimaryKey(String userId);
	  public boolean updateUserRole(TsysUSer user);
	  public  String selectCount();
	  public PageBean<VOUserPerson> getallcode2(String tUserName,String currPage,String pageSize);
}
