package com.ef.golf.mapper;

import com.ef.golf.pojo.Dynamic_praise;
import com.ef.golf.vo.QuanziUserVo;

import java.util.List;
import java.util.Map;

public interface Dynamic_praiseMapper {
    int deleteByPrimaryKey(Integer dynamicPraiseId);

    int insert(Dynamic_praise record);

    int insertSelective(Dynamic_praise record);

    Dynamic_praise selectByPrimaryKey(Integer dynamicPraiseId);

    int updateByPrimaryKeySelective(Dynamic_praise record);

    int updateByPrimaryKey(Dynamic_praise record);

    //根据动态id获取点赞记录
    /*List<QuanziUserVo> getPraises(Integer dynamicId);*/
    List<QuanziUserVo> getPraises(Map map);
    Integer getPraisesCount(Integer dynamicId);
    //查询是否已点赞
    List<Integer> selectPraisesRecord(Integer dynamicId);
    int delPraises(Map map);

}