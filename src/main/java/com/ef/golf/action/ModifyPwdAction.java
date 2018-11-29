package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
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
public class ModifyPwdAction extends AbstractService {


    @Resource
    private UserService userService;
    @Resource
    private RedisBaseDao redisBaseDao;


    //@NotNull
    private String phone;

   /* private String validateCode;*/

    //@NotNull
    private String newPwd;


    public Object doService() throws LoginException {


       /* String realValidateCode = redisBaseDao.get("code" + phone);
        System.out.println("realValidateCode" + realValidateCode);
        if (StringUtils.isEmpty(realValidateCode) || StringUtils.isEmpty(validateCode) || !validateCode.equals(realValidateCode))
            throw new LoginException(Constant.ERR_USER - 4);*/

        userService.updatePWD(phone, newPwd);
        return "";
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }*/

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
