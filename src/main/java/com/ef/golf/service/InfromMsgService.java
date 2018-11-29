package com.ef.golf.service;

import com.ef.golf.pojo.InfromMsg;
import com.ef.golf.util.PageBean;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/18 14:41
 */
public interface InfromMsgService {

    int deleteByPrimaryKey(Long infromId);

    int insert(InfromMsg record);

    int insertSelective(InfromMsg record);

    InfromMsg selectByPrimaryKey(Long infromId);

    int updateByPrimaryKeySelective(InfromMsg record);

    int updateByPrimaryKey(InfromMsg record);

    PageBean getPushAllInfromMsg(String userId, Integer pageNo, Integer pageSize);

    Integer getInfromMsgCount(String userId);
}
