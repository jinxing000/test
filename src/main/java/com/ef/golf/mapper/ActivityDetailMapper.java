package com.ef.golf.mapper;

import com.ef.golf.pojo.ActivityDetail;

public interface ActivityDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(ActivityDetail record);

    int insertSelective(ActivityDetail record);

    ActivityDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(ActivityDetail record);

    int updateByPrimaryKey(ActivityDetail record);

    ActivityDetail selectByKey(Integer activity_id);
}