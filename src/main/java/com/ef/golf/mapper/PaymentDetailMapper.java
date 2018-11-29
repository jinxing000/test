package com.ef.golf.mapper;


import com.ef.golf.pojo.PaymentDetail;

/**
 * Created by Administrator on 2018/6/14.
 */
public interface PaymentDetailMapper {
    int insert(PaymentDetail record);

    int insertSelective(PaymentDetail record);
}
