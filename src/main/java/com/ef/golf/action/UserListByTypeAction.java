package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户列表按类型
 * ios调用
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserListByTypeAction extends AbstractService {

    private Integer userId;
    private String userType;//2教练 3球童 4客户经理 5球会
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Resource
    private UserService userService;

    @Override
    public Object doService() throws Exception {
        PageBean pageBean = userService.queryUserListByType(userType, pageNo, pageSize, userId);
        return pageBean;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
