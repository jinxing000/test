package com.ef.golf.action;


import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.IntegralRecordService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.pingplusplus.model.Withdrawal;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
/**
 * 废弃
 * */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WithdrawAction extends AbstractService {

    @Resource
    private PingUtil pingUtil;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    private String sid;
    private String uid;

    private int amount;
    private int userFee = 0;
    private String description = "提现";
    private String channel;
    private String account;
    private String name;
    private String open_bank_code;
    private String prov;
    private String city;
    private String openId;


    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        String orderNo = pingUtil.generateOrderNo("t", uid);
        /*Withdrawal withdrawal = pingUtil.withdrawal(amount, userId.toString(), orderNo, userFee, description, channel, account, name, open_bank_code, prov, city);
        String withdrawalId = withdrawal.getId();*/
        return null;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserFee(int userFee) {
        this.userFee = userFee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpen_bank_code(String open_bank_code) {
        this.open_bank_code = open_bank_code;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
