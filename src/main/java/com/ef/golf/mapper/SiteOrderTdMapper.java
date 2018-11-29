package com.ef.golf.mapper;


import com.ef.golf.pojo.SiteOrderTd;

public interface SiteOrderTdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SiteOrderTd record);

    int insertSelective(SiteOrderTd record);

    SiteOrderTd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SiteOrderTd record);

    int updateByPrimaryKey(SiteOrderTd record);
}