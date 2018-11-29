package com.ef.golf.mapper;

import com.ef.golf.pojo.Cllect;
import org.apache.ibatis.annotations.Param;

public interface CllectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(Cllect record);

    int insertSelective(Cllect record);

    Cllect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(Cllect record);

    int updateByPrimaryKey(Cllect record);

    int countFavorite(@Param("goods_id") Integer goods_id, @Param("user_id") Integer user_id);
}