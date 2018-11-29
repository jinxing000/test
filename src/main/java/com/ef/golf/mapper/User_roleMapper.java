package com.ef.golf.mapper;

import com.ef.golf.pojo.User_role;

import java.util.List;

public interface User_roleMapper {
    int insert(User_role record);

    int insertSelective(User_role record);

    List<Integer> selectHuserIdByPermission(String name);
    List<Integer> selectUserIdByRoleId(String roleId);
}