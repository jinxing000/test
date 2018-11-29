package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.AccountService;
import com.ef.golf.util.SmsUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreatePingUserAction extends AbstractService {

    private String userId;
   @Resource
   private AccountService accountService;

    @Resource
    private PingUtil pingUtil;

    @Override
    public Object doService() throws Exception {
        try{
            pingUtil.createUser(userId);
            //创建容联云子账户
            SmsUtil.createUser(userId.toString());
            Account account = new Account(null,Long.valueOf(userId),new Date(),null,0.0,0.0,0.0,0.0,"1");
            accountService.saveAccount(account);
        }catch (Exception e){
            throw new SystemException(Constant.ERR_UNKNOW);
        }
        return IfunResult.build(0, "成功");
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
