package com.androidwatcher.dao;

import com.androidwatcher.model.RsaKey;
import com.androidwatcher.model.RsaKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RsaKeyMapper {
    long countByExample(RsaKeyExample example);

    int deleteByExample(RsaKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RsaKey record);

    int insertSelective(RsaKey record);

    List<RsaKey> selectByExample(RsaKeyExample example);

    RsaKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RsaKey record, @Param("example") RsaKeyExample example);

    int updateByExample(@Param("record") RsaKey record, @Param("example") RsaKeyExample example);

    int updateByPrimaryKeySelective(RsaKey record);

    int updateByPrimaryKey(RsaKey record);
}