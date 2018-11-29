package com.ef.golf.service;


import com.ef.golf.pojo.IntegralRule;

import java.util.Map;

public interface IntegralService {

    int insertForRegist(Long userId);
    Integer getUserTotalScore(Integer userId);
    Integer updateUserTotalScore(Map map);

    IntegralRule selectRuleByType(String type);
}
