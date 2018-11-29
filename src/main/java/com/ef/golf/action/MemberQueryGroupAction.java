package com.ef.golf.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Groups;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.GroupUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2018年5月28日 wxc
 * 查询群列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MemberQueryGroupAction extends AbstractService {


    @Resource
    private GroupService groupService;

    private String userName;
    private String target = "1";
    private String userId;

    @Override
    public Object doService() throws Exception {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userName", userName);
        parameterMap.put("target", target);
        Map<String, Object> map = GroupUtils.queryGroup(parameterMap);

        JSONArray jsonArray = (com.alibaba.fastjson.JSONArray) map.get("groups");
        List<Groups> list = new ArrayList<Groups>();
        list = JSONObject.parseArray(JSONObject.toJSONString(jsonArray), Groups.class);
        if (list != null) {
            for (Groups groups : list) {
                System.out.println(groups.getGroupId());
                /** 获取头像群主 */
                if (groups.getGroupId() != null && !"".equals(groups.getGroupId())) {
                    Groups groups1 = groupService.getHeadImg(groups.getGroupId());
                    List<Integer> rens = groupService.getGroupRen(groups.getGroupId());
                    if (StringUtils.isNotBlank(userId)) {
                        if (rens.contains(Integer.valueOf(userId))) {
                            groups.setHasIn(true);
                        } else {
                            groups.setHasIn(false);
                        }
                    }
                    if (groups1 != null && groups1.getHeadPortraitUrl() != null && groups1.getOwner() != null) {
                        groups.setHeadPortraitUrl(groups1.getHeadPortraitUrl());
                        groups.setOwner(groups1.getOwner());
                    }
                }
            }
        }
        try {
            if (!"000000".equals(map.get("statusCode"))) {
                list = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(Constant.ERR_QUERYGROUP);
        }
        return list;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
