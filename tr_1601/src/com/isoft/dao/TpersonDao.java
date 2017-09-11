package com.isoft.dao;

import com.isoft.model.Tperson;
import com.isoft.model.VOPersonAtt;

public interface TpersonDao {
    int deleteByPrimaryKey(String tPresonId);

    int insert(Tperson record);

    int insertSelective(Tperson record);

    Tperson selectByPrimaryKey(String tPresonId);

    int updateByPrimaryKeySelective(Tperson record);

    int updateByPrimaryKey(Tperson record);
    Tperson  selectByUserIda(String userId);
    VOPersonAtt selectByuserId(String userId);
     int  DelFromUseId(String userId);
     int uptDicIdeByuserid(Tperson person);
}