package com.ef.golf.service;

import com.ef.golf.pojo.CashBack;
import com.ef.golf.vo.CashBackVo;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/4/28 11:22
 */
public interface CashBackService {

    List<CashBack> selectCashBackMsg();
    List<CashBackVo>getCashBackScaleVo();
}
