package com.androidwatcher.dao;

import com.androidwatcher.model.SvmData;
import com.androidwatcher.model.SvmDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SvmDataMapper {
    long countByExample(SvmDataExample example);

    int deleteByExample(SvmDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvmData record);

    int insertSelective(SvmData record);

    List<SvmData> selectByExample(SvmDataExample example);

    SvmData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvmData record, @Param("example") SvmDataExample example);

    int updateByExample(@Param("record") SvmData record, @Param("example") SvmDataExample example);

    int updateByPrimaryKeySelective(SvmData record);

    int updateByPrimaryKey(SvmData record);
}