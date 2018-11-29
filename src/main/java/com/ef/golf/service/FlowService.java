package com.ef.golf.service;

import com.ef.golf.pojo.Flow;
import com.ef.golf.util.PageBean;

import java.util.Map;

public interface FlowService {

    int insertSelective(Flow flow,String channel);

    PageBean jiaoYiJiLu(Long userId,Integer pageNo,Integer pageSize);

    Double getTodayZenSongMoney(Integer userId);
}
