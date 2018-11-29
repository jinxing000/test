package com.ef.golf.mapper;

import com.ef.golf.pojo.QiuHuiControl_qiuTong;

public interface QiuHuiControl_qiuTongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QiuHuiControl_qiuTong record);

    int insertSelective(QiuHuiControl_qiuTong record);

    QiuHuiControl_qiuTong selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QiuHuiControl_qiuTong record);

    int updateByPrimaryKey(QiuHuiControl_qiuTong record);
}