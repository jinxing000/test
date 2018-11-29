package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.InfromMsgService;
import com.ef.golf.service.WorkTypeService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 消息列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InfromMessageListAction extends AbstractService {

    private String userId;
    private Integer pageNum = 1;
    private Integer showCount = 5;
    @Resource
    private InfromMsgService infromMsgService;

    @Override
    public Object doService() throws Exception {
            PageBean pageBean = infromMsgService.getPushAllInfromMsg(userId,pageNum,showCount);
       return pageBean;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
