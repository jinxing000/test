package com.ef.golf.mapper;

import com.ef.golf.pojo.Banner;

import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    //根据分组名称查询banner集合
    List<Banner> bannerByGrouping(String grouping);
}