package com.ef.golf.service.impl;

import com.ef.golf.mapper.User_roleMapper;
import com.ef.golf.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/8/28 15:50
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private User_roleMapper user_roleMapper;

    @Override
    public List<Integer> selectHuserIdByPermission(String name) {
        return user_roleMapper.selectHuserIdByPermission(name);
    }

    @Override
    public List<Integer> selectUserIdByRoleId(String roleId) {
        return user_roleMapper.selectUserIdByRoleId(roleId);
    }
}
