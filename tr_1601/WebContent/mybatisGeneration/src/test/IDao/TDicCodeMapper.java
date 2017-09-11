package test.IDao;

import test.domain.TDicCode;

public interface TDicCodeMapper {
    int deleteByPrimaryKey(String tDicId);

    int insert(TDicCode record);

    int insertSelective(TDicCode record);

    TDicCode selectByPrimaryKey(String tDicId);

    int updateByPrimaryKeySelective(TDicCode record);

    int updateByPrimaryKey(TDicCode record);
}