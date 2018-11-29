package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.HopeService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HopeListAction extends AbstractService {

    @Resource
    private HopeService hopeService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    private String uid;
    private String sid;
    private String hopeState;//1 实现中 2 已实现 3 已失效
    private int pageNo = 1;
    private int pageSize = 5;

    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        PageBean pageBean = hopeService.getHopeList(userId, hopeState, pageNo, pageSize);
        return pageBean;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setHopeState(String hopeState) {
        this.hopeState = hopeState;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
