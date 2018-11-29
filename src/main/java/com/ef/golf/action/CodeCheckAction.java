package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.util.RandomUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisExpireTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CodeCheckAction extends AbstractService {


    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull
    private String phone;

    private String validateCode;

    private String uid;


    public Object doService() throws LoginException {
        Map<String,Object>map = new HashMap<>();

        String realValidateCode = redisBaseDao.get("code" + phone);
        System.out.println("realValidateCode" + realValidateCode);
        if (StringUtils.isEmpty(realValidateCode) || StringUtils.isEmpty(validateCode) || !validateCode.equals(realValidateCode)){
            throw new LoginException(Constant.ERR_USER - 4);
        }else{
            map.put("status",0);
            map.put("message","ok");
            String codeToken = "token"+ RandomUtil.getRandom(3);
            redisBaseDao.saveEx(uid+"codeToken",codeToken, RedisExpireTime.SmsCode.getSeconds());
            map.put("token",codeToken);
        }
        return map;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
