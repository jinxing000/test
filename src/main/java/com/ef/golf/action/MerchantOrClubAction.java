package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by wxc
 * 商家球会
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantOrClubAction extends AbstractService {

    @Resource
    private UserService userService;
    private String nickname;
    private String userType;//6门店 7球会商家
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Override
    public Object doService() throws Exception {
        PageBean pageBean = userService.selectClubOrShop(nickname, userType, pageNo, pageSize,0);
        return pageBean;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
