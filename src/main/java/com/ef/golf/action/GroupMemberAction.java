package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.ef.golf.action
 * Administrator
 * 2018/5/29 18:54
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroupMemberAction extends AbstractService {
    //@NotNull
    private String groupId;
    private Integer pageNum = 1;
    private Integer showCount = 5;
    @Resource
    private GroupService groupService;


    @Override
    public Object doService() throws Exception {
        PageBean pageBean = groupService.getGroupMemer(groupId, pageNum, showCount);
        return pageBean;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
