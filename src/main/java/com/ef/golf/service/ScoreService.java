package com.ef.golf.service;


import com.ef.golf.pojo.Score_attr;

import java.util.List;

/**
 * create by xzw
 * 2017年10月17日15:12:07
 * 评分表
 */
public interface ScoreService {

    /**
     * @param attr_id 评分名称id
     * @param score 评分分数
     * @param order_id 订单id
     * @return
     */
    int doComment(Integer order_id,Integer[] attr_id,Integer[] score);

    /**
     * @param order_id 订单ID
     * @return ture 已存在 false 不存在
     * 查看订单是否已经被评分
     */
    boolean selectHaveScore(Integer order_id);
    /**
     * @param attrAscription 评分归属
     * @return List<Score_attr>
     * */
    List<Score_attr> selectScoreAttr(String attrAscription);
}
