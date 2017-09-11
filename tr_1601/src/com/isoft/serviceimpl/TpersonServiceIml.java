package com.isoft.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoft.dao.TdicCodeDao;
import com.isoft.dao.TpersonDao;
import com.isoft.model.TdicCode;
import com.isoft.model.Tperson;
import com.isoft.model.VOPersonAtt;
import com.isoft.service.TpersonService;
@Service
public class TpersonServiceIml implements TpersonService {
@Resource
private TpersonDao persondao;
@Resource
private TdicCodeDao codedao;

@Override
public Tperson selectByUserIda(String userId) {
	// TODO Auto-generated method stub
	return persondao.selectByUserIda(userId);
}

@Override
public List<TdicCode> selectTdlist() {
	// TODO Auto-generated method stub
	return codedao.getDicType();
}

@Override
public VOPersonAtt selectByuserId(String userId) {
	// TODO Auto-generated method stub
	return persondao.selectByuserId(userId);
}

@Override
public boolean updateByPrimaryKeySelective(Tperson person) {
	// TODO Auto-generated method stub
	return persondao.updateByPrimaryKeySelective(person)>0?true:false;
}

@Override
public boolean DelFromUseId(String userId) {
	// TODO Auto-generated method stub
	return persondao.DelFromUseId(userId)>0?true:false;
}

@Override
public boolean uptDicIdeByuserid(Tperson person) {
	// TODO Auto-generated method stub
	return persondao.uptDicIdeByuserid(person)>0?true:false;
}

@Override
public boolean insertSelective(Tperson person) {
	// TODO Auto-generated method stub
	return persondao.insertSelective(person)>0?true:false;
}

}
