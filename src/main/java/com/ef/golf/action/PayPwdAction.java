package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.UserService;
import com.ef.golf.util.AESCoder;
import com.ef.golf.util.MD5;
import com.ef.golf.util.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置支付密码，修改支付密码
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayPwdAction extends AbstractService {

    @Resource
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;

    private Integer userId;
    private Integer type;// 1-设置支付密码 2-修改支付密码 3-验证码方式
    private String newPassword;//新密码
    private String jiuPassword;//旧密码
    private String unix;//时间戳
    private String token;
    private String uid;

    @Override
    public Object doService() throws Exception {
        if(null== type){
            type = 3;
        }
        Map<String,Object>map = new HashMap<>();

        if(1==type){//设置支付密码
            User user = userService.selectByPrimaryKey(userId.longValue());
            /** 加密 */
            Map<String,Object>passwordMap = AESCoder.jia(newPassword);
                user.setPasswordPay((String) passwordMap.get("encryptData"));
                int status = userService.updateByPrimaryKey(user);
                if(status>0){
                    map.put("status",0);
                    map.put("message","设置成功");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
        }else if(2 == type){//修改支付密码
            User user = userService.selectByPrimaryKey(userId.longValue());
            String[]pwds = user.getPasswordPay().split(",");
            /** 解密 */
            String userPwd = new String(AESCoder.jie(AESCoder.parseHexStr2Byte(pwds[0]),AESCoder.parseHexStr2Byte(pwds[1])));

            if(jiuPassword.equalsIgnoreCase(MD5.encode("iFunGolf_"+userPwd+"+"+unix.substring(unix.length()-5,unix.length())+"*FollowMe"))){
                Map<String,Object>passwordMap = AESCoder.jia(newPassword);
                user.setPasswordPay((String) passwordMap.get("encryptData"));
                int status = userService.updateByPrimaryKey(user);
                if(status>0){
                    map.put("status",0);
                    map.put("message","修改成功");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
            }else{
                throw new SystemException(Constant.ERR_JIU_PWD);
            }
        }else{
            if(redisBaseDao.exist(uid+"codeToken")){
                String codeToken = redisBaseDao.get(uid+"codeToken");
                if(codeToken.equals(token)){
                    User user = userService.selectByPrimaryKey(userId.longValue());
                    Map<String,Object>passwordMap = AESCoder.jia(newPassword);
                    user.setPasswordPay((String) passwordMap.get("encryptData"));
                    int status = userService.updateByPrimaryKey(user);
                    if(status>0){
                        map.put("status",0);
                        map.put("message","修改成功");
                    }else{
                        throw new SystemException(Constant.ERR_UPDATE);
                    }
                }else{
                    throw new SystemException(Constant.ERR_UPDATE_PAYPWD_MESSAGE);
                }
            }else{
                throw new SystemException(Constant.ERR_UPDATE_PAYPWD_MESSAGE);
            }
        }
                return map;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setJiuPassword(String jiuPassword) {
        this.jiuPassword = jiuPassword;
    }

    public void setUnix(String unix) {
        this.unix = unix;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
