package com.ef.golf.mapper;

import com.ef.golf.pojo.Logo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogoMapper {
    int deleteByPrimaryKey(Integer logoId);

    int insert(Logo record);

    int insertSelective(Logo record);

    Logo selectByPrimaryKey(Integer logoId);

    int updateByPrimaryKeySelective(Logo record);

    int updateByPrimaryKey(Logo record);

    List<Logo> findAppIndexIcoByVersion(@Param("platform") String platform, @Param("versionName")String versionName);
}