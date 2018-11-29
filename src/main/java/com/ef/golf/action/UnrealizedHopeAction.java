package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by xzw
 * 2018年1月3日14:15:05
 * 获取未实现的愿望接口
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnrealizedHopeAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private HopeService hopeService;

    private String uid;
    private String sid;

    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        List<Hope> getHopeList = hopeService.getUnrealizedHopes(userId);
        return getHopeList;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
