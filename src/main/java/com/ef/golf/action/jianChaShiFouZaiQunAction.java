package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.GroupService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * com.ef.golf.action
 * Administrator
 * 2018/9/17 万晓超
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class jianChaShiFouZaiQunAction extends AbstractService {
    //@NotNull
    private String groupId;
    //@NotNull
    private String requestUserId;
    @Resource
    private GroupService groupService;


    @Override
    public Object doService() throws Exception {
        Map<String,Object>map = new HashMap<>();
        int count = groupService.jianChaShiFouZaiQun(requestUserId,groupId);
        if(count>0){
            map.put("status",1);
        }else{
            map.put("status",0);
        }
        return map;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }
}
