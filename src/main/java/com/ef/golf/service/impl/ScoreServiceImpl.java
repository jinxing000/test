package com.ef.golf.service.impl;

import com.ef.golf.mapper.ScoreMapper;
import com.ef.golf.mapper.Score_attrMapper;
import com.ef.golf.pojo.Score_attr;
import com.ef.golf.service.ScoreService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private Score_attrMapper score_attrMapper;

    public int doComment(Integer order_id,Integer[] attr_id, Integer[] score) {
        int end=0;
        //判断评分项是否评分
        if(attr_id.length>0){
            for (int i=0;i<attr_id.length;i++){
                end+=scoreMapper.doComment(order_id,attr_id[i],score[i]);
            }
        }
        return end;
    }

    public boolean selectHaveScore(Integer order_id) {
        boolean flag=true;
       int end= scoreMapper.selectHaveScore(order_id);
       if(end<=0){
           flag=false;
       }
        return flag;
    }
    /**
     * @param attrAscription 评分归属
     * @return List<Score_attr>
     * */
    @Override
    public List<Score_attr> selectScoreAttr(String attrAscription) {
        return score_attrMapper.selectScoreAttr(attrAscription);
    }
}
