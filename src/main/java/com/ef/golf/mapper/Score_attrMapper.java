package com.ef.golf.mapper;

import com.ef.golf.pojo.Score_attr;

import java.util.List;

public interface Score_attrMapper {
    int deleteByPrimaryKey(Integer attrId);

    int insert(Score_attr record);

    int insertSelective(Score_attr record);

    Score_attr selectByPrimaryKey(Integer attrId);

    int updateByPrimaryKeySelective(Score_attr record);

    int updateByPrimaryKey(Score_attr record);

    List<Score_attr>selectScoreAttr(String attrAscription);
}