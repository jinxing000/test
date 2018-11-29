package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.util.RandomUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisExpireTime;
import com.ef.golf.util.SmsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class
SmsCodeAction extends AbstractService {

    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull
    private String phoneNumber;


    public Object doService() throws QueryException, SystemException {

        //获取验证码
        String randomNumber = RandomUtil.getRandom(4);
        //如果获取的随机验证码（randomNumber）不为空，将随机验证码存入redis
        //if(randomNumber!=null&&randomNumber!=""){
        if (StringUtils.isNotEmpty(randomNumber)) {
            //redisBaseDao.save("code"+phoneNumber,randomNumber);
            redisBaseDao.saveEx("code" + phoneNumber, randomNumber, RedisExpireTime.SmsCode.getSeconds());
            System.out.println(redisBaseDao.get("code" + phoneNumber));
        } else {
            throw new QueryException(Constant.ERR_SYSTEM);
        }
        //开始发送短信验证
        boolean end = SmsUtil.sendMessage(phoneNumber, randomNumber);
        if (end) {
            return end;
        } else {
            throw new SystemException(Constant.ERR_CODE_SEND_FAILED);
        }

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
