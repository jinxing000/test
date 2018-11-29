package com.ef.golf.mapper;

import com.ef.golf.pojo.Share;
import com.ef.golf.vo.QuanziUserVo;

import java.util.List;
import java.util.Map;

public interface ShareMapper {
    int deleteByPrimaryKey(Integer shareId);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer shareId);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);

    //根据动态id获取分享的记录信息
    /*List<QuanziUserVo> getShares(Integer dynamicId);*/
    List<QuanziUserVo> getShares(Map map);
    Integer getSharesCount(Integer dynamicId);
}