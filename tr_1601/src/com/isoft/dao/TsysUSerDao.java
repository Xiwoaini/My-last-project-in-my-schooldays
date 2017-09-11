package com.isoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoft.model.*;

public interface TsysUSerDao {
    int deleteByPrimaryKey(String tUserId);

    int insert(TsysUSer record);

    int insertSelective(TsysUSer record);

    TsysUSer selectByPrimaryKey(String tUserId);

    int updateByPrimaryKeySelective(TsysUSer record);

    int updateByPrimaryKey(TsysUSer record);
    TsysUSer selectByuserName(String name);
    TsysUSer selectByuser(TsysUSer user);
    List<TsysUSer> selDisticRole();
   TsysUSer selUserByPwd(TsysUSer user);
    int   updatePassword(TsysUSer user);
    TsysUSer asUserRole(String userId);
   List<VOUserPerson> selectUserPerson(@Param("tUserName")String tUserName,@Param("nextStartPage")int nextStartPage,@Param("v_pageSize")int v_pageSize );
   int updateStatus(TsysUSer user);
    int updateUserRole(TsysUSer user);
    String selectCount();
    String  selectUserPersonCount(@Param("tUserName")String tUserName);
}