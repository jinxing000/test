package com.ef.golf.service;

import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.User;
import com.ef.golf.pojo.UserFeedback;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.SearchCaddieResult;
import com.ef.golf.vo.*;

import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/20.
 * Date: 2017/9/20 16:16
 */

public interface UserRoleService {

    List<Integer> selectHuserIdByPermission(String name);
    List<Integer> selectUserIdByRoleId(String roleId);
}
