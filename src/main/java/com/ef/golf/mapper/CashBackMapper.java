package com.ef.golf.mapper;

import com.ef.golf.pojo.CashBack;
import com.ef.golf.vo.CashBackVo;

import java.util.List;

public interface CashBackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashBack record);

    int insertSelective(CashBack record);

    CashBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashBack record);

    int updateByPrimaryKey(CashBack record);
    List<CashBack> selectCashBackMsg();
    List<CashBackVo>getCashBackScaleVo();
}