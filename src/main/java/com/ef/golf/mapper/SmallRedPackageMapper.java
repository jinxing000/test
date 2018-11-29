package com.ef.golf.mapper;

import com.ef.golf.pojo.SmallRedPackage;

import java.util.List;

public interface SmallRedPackageMapper {
    int deleteByPrimaryKey(String bigRedPackageId);

    int insert(SmallRedPackage record);

    int insertSelective(SmallRedPackage record);

    List<SmallRedPackage> selectByPrimaryKey(String bigRedPackageId);

    int updateByPrimaryKeySelective(SmallRedPackage record);

    int updateByPrimaryKey(SmallRedPackage record);

    Integer smallRedPackageCount(String redPackageId);
}