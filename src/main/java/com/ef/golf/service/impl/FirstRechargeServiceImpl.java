package com.ef.golf.service.impl;

import com.ef.golf.mapper.FirstRechargeMapper;
import com.ef.golf.pojo.FirstRecharge;
import com.ef.golf.service.FirstRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/10/22 13:51
 */
@Service
public class FirstRechargeServiceImpl implements FirstRechargeService {

    @Autowired
    private FirstRechargeMapper firstRechargeMapper;

    @Override
    public List<FirstRecharge> selectAllFirstRecharge() {
        return firstRechargeMapper.selectAllFirstRecharge();
    }
}
