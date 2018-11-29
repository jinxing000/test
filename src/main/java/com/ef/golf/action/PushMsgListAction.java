package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.WorkTypeService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 消息列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PushMsgListAction extends AbstractService {

    private Integer userId;
    private Integer pageNum = 1;
    private Integer showCount = 5;
    @Resource
    private WorkTypeService workTypeService;

    @Override
    public Object doService() throws Exception {
       PageBean pageBean =  workTypeService.getPushMsgList(userId,pageNum,showCount);
       return pageBean;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
