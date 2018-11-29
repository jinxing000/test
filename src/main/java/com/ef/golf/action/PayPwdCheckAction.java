package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.UserService;
import com.ef.golf.util.AESCoder;
import com.ef.golf.util.MD5;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayPwdCheckAction extends AbstractService {

    @Resource
    private UserService userService;

    private Integer userId;
    private String password;
    private String unix;

    //iFunGolf_%@+%@*FollowMe
    //[NSString stringWithFormat:@"iFunGolf_%@+%@+Pwd",timeStamp,is?@"Correct":@"NotCorrect"]
    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectByPrimaryKey(userId.longValue());
        String[] pwds = user.getPasswordPay().split(",");
        String userPwd = new String(AESCoder.jie(AESCoder.parseHexStr2Byte(pwds[0]), AESCoder.parseHexStr2Byte(pwds[1])));
        Date date = new Date();
        String time = date.getTime() + "";
        String md5Pwd = MD5.encode("iFunGolf_" + userPwd + "+" + unix.substring(unix.length() - 5, unix.length()) + "*FollowMe");
        System.out.println(md5Pwd);
        /*String md5Pwd2 = MD5.encode("iFunGolf_" + password + "+" + unix.substring(unix.length() - 5, unix.length()) + "*FollowMe");*/
        System.out.println(unix.substring(unix.length() - 5, unix.length()));
        System.out.println("iFunGolf_" + time.substring(time.length() - 5, time.length()) + "+Correct+Pwd");
        if (password.equalsIgnoreCase(md5Pwd)) {
            map.put("status",0);
            map.put("result", MD5.encode("iFunGolf_" + time.substring(time.length() - 5, time.length()) + "+Correct+Pwd"));
            map.put("unix", time);
        } else {
            /*throw new SystemException(Constant.ERR_ERRPWD);*/
            map.put("status", 1);

            map.put("message", "密码有误");
        }
        return map;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUnix(String unix) {
        this.unix = unix;
    }
}
