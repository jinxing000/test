package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CollectService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziGetCollectNumAction extends AbstractService {
    private String type;//类型：1查询关注我的数量 2查询我关注的数量
    private String uid;
    private String sid;


    @Resource
    private CollectService collectService;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;


    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        int num = 0;
        if (this.type.equals("1")) {
            num = collectService.getCollectMineNum(userId);
        } else {
            num = collectService.getCollectPersionNum(userId);
        }
        return num;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
