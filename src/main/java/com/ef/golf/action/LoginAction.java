package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class LoginAction extends AbstractService {


    @Resource
    private UserService userService;

    @Resource
    private RedisBaseDao redisBaseDao;


    /*@NotNull(message = "用户名不能为空!")*/
    private String userName;

    /*@NotNull(message = "密码不能为空!")*/
    private String userPwd;//116.404269,39.914935

    private String lat = "39.914935";//j

    private String lng = "116.404269";//w

    private String loginType = "1";//登录类型（1.手机用户 2.web端 3.微信）

    private String openId;//微信登录标识

    public Object doService() throws LoginException, SystemException {
        /*if (null==userName||"".equals(userName)&&null==userPwd||"".equals(userPwd)&&null==loginType||"".equals(loginType)&&null==openId||"".equals(openId)){
            throw new SystemException(Constant.ERR_UNKNOW);
        }*/

        return userService.userLogin(userName, userPwd, lat, lng, loginType,openId);
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
