package com.ef.golf.mapper;

import com.ef.golf.pojo.Score;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer scoreId);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    /**
     *
     * @param attr_id 评分项id
     * @param score 评分
     * @param order_id 订单ID
     * @return
     */
    int doComment(@Param("order_id")Integer order_id,@Param("attr_id")Integer attr_id, @Param("score")Integer score);

    /**
     *
     * @param order_id 订单ID
     * @return
     * 查看订单是否被评分
     */
    Integer selectHaveScore(Integer order_id);
}