package com.ef.golf.mapper;

import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.PaymentLog;

import java.util.Map;

public interface EsPaymentLogsMapper {


    int insert(PaymentLog record);

    int insertSelective(PaymentLog record);

    int update(PaymentLog record);
//根据order_id 获取应收id
    PaymentLog getPaymentLogId(Integer orderId);
}