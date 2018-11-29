package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.DynamicService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * create by xzw
 * 2018年1月10日10:19:48
 * 删除圈子的动态
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziDynamicDel extends AbstractService {

    @Resource
    private DynamicService dynamicService;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    //@NotNull
    private String dynamicId;
    private String uid;
    private String sid;


    @Override
    public Object doService() throws Exception {
        redisLoginVerifyUtil.longinVerifty(sid, uid);
        dynamicService.updateDynamicForDel(Integer.valueOf(dynamicId));
        return new UserVo(sid, uid);
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
