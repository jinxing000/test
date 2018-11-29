package com.ef.golf.util;


import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.exception.LoginException;
import com.ef.golf.service.UserService;
import javafx.beans.binding.LongExpression;
import javafx.fxml.LoadException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * create by xzw
 * 2017年12月20日09:28:20
 * 用于redis验证用户是否登录的验证
 *
 */
@Repository
public class RedisLoginVerifyUtil {

    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private UserService userService;


    /**
     *通过redis判断用户是否登录，并获取登录用户的id
     * @param sid  sessionId
     * @param uid  登录账号，本系统为用户手机号
     * @return  用户id
     */
    public Integer longinVerifty(String sid,String uid) throws LoginException {
        if (StringUtils.isNotEmpty(sid)) {
            String uuid=redisBaseDao.get(sid);
            if(StringUtils.isEmpty(uuid)){
                throw new LoginException(Constant.ERR_PARAMETER);
            }
            if(uuid.equals(uid)){
                Long userId=userService.getUid(uid);
                return userId.intValue();
            }else{
                throw new LoginException(Constant.ERR_USER);
            }
        } else {
            throw new LoginException(Constant.ERR_PARAMETER);
        }
    }


    /**
     * 重置session有效时间
     * sid:sessionId
     */
    public boolean resetSessionIdEffectivity(String sid){
        boolean flag=false;
        if(StringUtils.isNotEmpty(sid)){
            redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
            flag=true;
        }
        return flag;
    }

}
