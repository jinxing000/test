package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.QuanZiNewsService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by xzw
 * 2017年12月20日11:44:00
 * 清除我的消息
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClearNewsAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private QuanZiNewsService quanZiNewsService;

    //@NotNull(message = "sessionId不能为空")
    private String sid;//sessionId

    //@NotNull(message = "账号不能为空")
    private String uid;//用户账号（手机号）

    private String nsid;//消息id（如果需要删除单条消息，则传入nsid，否则删除全部消息）

    @Override
    public Object doService() throws Exception {
        //判断用户是否登录
        long userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        //如果nsid为空，则删除所有消息，否则，删除单条消息
        int num = 0;
        if (StringUtils.isNotEmpty(nsid)) {
            num = quanZiNewsService.delNewsByNewsId(Integer.valueOf(nsid));
        } else {
            num = quanZiNewsService.delNewsByUserId(Integer.valueOf(String.valueOf(userId)));
        }
        if (num > 0) {
            return null;
        } else {
            throw new SystemException(Constant.ERR_UPDATE);
        }
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }
}
