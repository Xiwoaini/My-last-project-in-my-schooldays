package com.isoft.serviceimpl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.isoft.dao.TsysUSerDao;
import com.isoft.model.PageBean;
import com.isoft.model.TsysUSer;
import com.isoft.model.VOUserPerson;
import com.isoft.service.TsysuserService;
//import com.isoft.util.Page1;

@Service
public class TsysUserServiceIml implements TsysuserService {
@Resource
private TsysUSerDao userdao;

@Override
public boolean selectByuserName(String name) {
	// TODO Auto-generated method stub
	return userdao.selectByuserName(name)!=null?true:false;
}

@Override
public TsysUSer selectByuser(TsysUSer user) {
	// TODO Auto-generated method stub
	return userdao.selectByuser(user);
}

@Override
public boolean insertSelective(TsysUSer record) {
	// TODO Auto-generated method stub
	record.settRole("2");
	int i =new Random().nextInt(300);
    String cpId="u"+i;//设置Userid
    record.settUserId(cpId);
    record.settStatus("-1");
    return userdao.insertSelective(record)>0?true:false;
	
}

@Override
public List<TsysUSer> selDisticRole() {
	// TODO Auto-generated method stub
	return userdao.selDisticRole();
}

@Override
public boolean selUserByPwd(TsysUSer user) {
	// TODO Auto-generated method stub
	return userdao.selUserByPwd(user)!=null?true:false;
}

@Override
public boolean updatePassword(TsysUSer user) {
	// TODO Auto-generated method stub
	return userdao.updatePassword(user)>0?true:false;
}

@Override
public TsysUSer asUserRole(String userId) {
	// TODO Auto-generated method stub
	return userdao.asUserRole(userId);
}



@Override
public boolean updateStatus(TsysUSer user) {
	// TODO Auto-generated method stub
	return userdao.updateStatus(user)>0?true:false;
}

@Override
public boolean deleteByPrimaryKey(String userId) {
	// TODO Auto-generated method stub
	return userdao.deleteByPrimaryKey(userId)>0?true:false;
}

@Override
public boolean updateUserRole(TsysUSer user) {
	// TODO Auto-generated method stub
	return userdao.updateUserRole(user)>0?true:false;
}

@Override
public String selectCount() {
	// TODO Auto-generated method stub
	return userdao.selectCount();
}

//@Override
//public String selectUserPerson(String username, Page1 paper) {
//	// TODO Auto-generated method stub
//	return new Gson().toJson(userdao.selectUserPerson(username, paper.getNextStartPage(),paper.getV_pageSize()));
//	
//}

@Override
public PageBean<VOUserPerson> getallcode2(String tUserName, String currPage, String pageSize) {
	// TODO Auto-generated method stub
	 int cuttpage1=Integer.parseInt(currPage);
	 int pageSize1=Integer.parseInt(pageSize);
	 int nextStartPage1=cuttpage1*pageSize1;
	List<VOUserPerson>list= userdao.selectUserPerson(tUserName, nextStartPage1, pageSize1);
	int total= Integer.parseInt( userdao.selectUserPersonCount(tUserName));
	return new PageBean<>(list, cuttpage1, pageSize1, total);
}

}
