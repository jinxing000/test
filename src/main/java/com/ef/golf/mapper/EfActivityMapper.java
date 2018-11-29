package com.ef.golf.mapper;

import com.ef.golf.pojo.EfActivity;
import com.ef.golf.vo.ActivityMessageVo;

import java.util.List;

public interface EfActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(EfActivity record);

    int insertSelective(EfActivity record);

    EfActivity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(EfActivity record);

    int updateByPrimaryKey(EfActivity record);

    List<ActivityMessageVo>getActivityMessage(String typeOne);
}