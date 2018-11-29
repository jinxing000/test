package com.ef.golf.mapper;


import com.ef.golf.pojo.InfromMsg;
import com.ef.golf.vo.InfromMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfromMsgMapper {
    int deleteByPrimaryKey(Long infromId);

    int insert(InfromMsg record);

    int insertSelective(InfromMsg record);

    InfromMsg selectByPrimaryKey(Long infromId);

    int updateByPrimaryKeySelective(InfromMsg record);

    int updateByPrimaryKey(InfromMsg record);

    List<InfromMsgVo> getPushAllInfromMsg(@Param("userId") String userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    Integer getPushAllInfromMsgCount(String userId);
    Integer getInfromMsgCount(String userId);
}