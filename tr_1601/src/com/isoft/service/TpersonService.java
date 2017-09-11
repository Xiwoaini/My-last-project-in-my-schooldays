package com.isoft.service;


import java.util.List;

import com.isoft.model.*;

public interface TpersonService {
  public Tperson  selectByUserIda(String userId);
  public List<TdicCode> selectTdlist();
  public VOPersonAtt selectByuserId(String userId);
  public boolean updateByPrimaryKeySelective(Tperson person);
   public boolean  DelFromUseId(String userId);
   public boolean uptDicIdeByuserid(Tperson person);
   public boolean insertSelective(Tperson person);
}
