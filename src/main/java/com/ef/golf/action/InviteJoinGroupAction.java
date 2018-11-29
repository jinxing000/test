package com.ef.golf.action;

import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Group;
import com.ef.golf.pojo.Groups;
import com.ef.golf.service.GroupService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.GroupUtils;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 2018年5月28日 wxc
 * 加群处理
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InviteJoinGroupAction extends AbstractService {

    @Resource
    private GroupService groupService;
    @Resource
    private UserService userService;

    private String userName;//自定义账号或通讯账号
    private String groupId;//群组ID
    private String confirm = "1";//可选 是否需要被邀请人确认  0 ：需要 1：不需要（自动加入群组）缺省1
    private String members;//被邀请成员，一次最多可以邀请50人，且邀请的成员必须是已经在客户端登陆过的用户。
    private String declared;//邀请理由，最长为50个字符
    private String userId;

    @Override
    public Object doService() throws Exception {
        //

        Map<String, Object> parameterMap = new HashMap<>();
        //群组管理员邀请用户加入群组
        Groups groups = groupService.getHeadImg(groupId);
        //parameterMap.put("userName", groups.getOwner());//必选	自定义账号或通讯账号

        Group group = groupService.selectGroupByGroupId(groupId);
        if(null!=group){
            userName = group.getQiuHuiId()+"";
        }
        parameterMap.put("userName",userName);
        parameterMap.put("groupId", groupId);//必选	群组ID
        String[] arrayStr = {members};
        parameterMap.put("members", Arrays.asList(arrayStr));//必选	被邀请成员，一次最多可以邀请50人，且邀请的成员必须是已经在客户端登陆过的用户。
        parameterMap.put("confirm", confirm);//可选 是否需要被邀请人确认  0 ：需要 1：不需要（自动加入群组）缺省1
        parameterMap.put("declared", declared);//可选	邀请理由，最长为50个字符
        Map<String, Object> map = GroupUtils.InviteJoinGroup(parameterMap);
        if (!"000000".equals(map.get("statusCode"))) {
            throw new SystemException(Constant.ERR_GROUP);
        } else {
            groupService.insertGroupUser(Integer.valueOf(members), groupId);
            Map<String, Object> serviceMap = new HashMap<>();
            MineVo mineVo = userService.getInfo(Integer.valueOf(userId));
            MineVo mineVo1 = userService.getInfo(Integer.valueOf(members));
            ServicePushUtils.servicePush(groupId, "12", new String[]{groupId},
                    mineVo.getNickName()+"邀请了"+mineVo1.getNickName()+"进入了群组", serviceMap);
        }
        return map;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
