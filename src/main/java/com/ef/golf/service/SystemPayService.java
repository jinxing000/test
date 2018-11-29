package com.ef.golf.service;

import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.pojo.System_pay;

import java.util.Map;

/**
 * create by xzw
 * 2018年2月27日16:45:53
 * 系统支付记录（保存给球童打赏记录）
 */
public interface SystemPayService {

    //新增一条给球童打赏的记录
    int insertSelective(System_pay record,String orderNo,Double oldMoney);
    int insertSelective(JiaoYiHuizong record);
}
