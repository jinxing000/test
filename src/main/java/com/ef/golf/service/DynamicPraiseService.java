package com.ef.golf.service;

import com.ef.golf.pojo.Dynamic_praise;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziUserVo;

import java.util.List;

/**
 * Created by xzw on 2017/12/28.
 * 动态点赞记录表
 */

public interface DynamicPraiseService {

    //新增一条动态点赞记录
    int insertSelective(Dynamic_praise record);

    //根据动态id获取点赞记录
    PageBean getPraises(Integer dynamicId, Integer pageNo, Integer pageSize,Integer userId);

    //查询点赞人
    List<Integer> selectPraisesRecord(Integer dynamicId);
    //取消点赞
    int delPraises(Integer userId,Integer dynamicId);
}
