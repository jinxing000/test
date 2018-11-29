package com.ef.golf.mapper;

import com.ef.golf.pojo.Es_Settings;

import java.util.Map;

public interface Es_SettingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Es_Settings record);

    int insertSelective(Es_Settings record);

    Es_Settings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Es_Settings record);

    int updateByPrimaryKey(Es_Settings record);

    Map getSettingPoint();
}