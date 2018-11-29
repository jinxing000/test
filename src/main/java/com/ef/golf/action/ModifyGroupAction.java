package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.UserService;
import com.ef.golf.util.GroupUtils;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ModifyGroupAction extends AbstractService {

    private String userName;//必选	自定义账号或通讯账号
    private String groupId;//必选	群组ID
    private String permission;//可选 申请加入模式 0：默认直接加入 1：需要身份验证 2：私有群组    缺省0
    private String name;//必选 群组名字，最长50个字符
    private String declared;//可选	群组公告，最长为200个字符
    private String groupDomain;//用户扩展字段
    @Resource
    private UserService userService;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> ServiceMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        Map<String, Object> resultMap = null;
        parameterMap.put("userName", userName);//必选	自定义账号或通讯账号
        parameterMap.put("groupId", groupId);//必选	群组ID
        parameterMap.put("permission", permission);//可选 申请加入模式 0：默认直接加入 1：需要身份验证 2：私有群组    缺省0
        parameterMap.put("name", name);//必选 群组名字，最长50个字符
        parameterMap.put("declared", declared);//可选	群组公告，最长为200个字符
        parameterMap.put("groupDomain", groupDomain);//用户扩展字段
        Map<String, Object> map = GroupUtils.ModifyGroup(parameterMap);
        try {
            if ("000000".equals(map.get("statusCode"))) {
                MineVo mineVo = userService.getInfo(Integer.valueOf(userName));
                String[] receiver = {groupId};//群组仅支持一个
                resultMap = ServicePushUtils.servicePush("26", "1", receiver, mineVo.getNickName() + "修改群名为" + name, ServiceMap);
            } else {
                throw new SystemException(Constant.ERR_GROUP);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(Constant.ERR_GROUP);
        }
        return resultMap;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public void setGroupDomain(String groupDomain) {
        this.groupDomain = groupDomain;
    }
}
