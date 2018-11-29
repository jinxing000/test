package com.ef.golf.action;

import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Groups;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.GroupUtils;
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
 * 加群处理
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QunChuLiAction extends AbstractService {

    @Resource
    private GroupService groupService;

    private String type;//1 管理员验证用户申请加入群组 2 用户验证管理员邀请加入群组 3 群组管理员删除成员 4 成员主动退出群组
    private String userName;//自定义账号或通讯账号
    private String groupId;//群组ID
    private String asker;//申请成员的帐号
    private String confirm;//0 ：通过 1：拒绝
    private String members;//待删除成员  List

    @Override
    public Object doService() throws Exception {
        Map<String,Object> map= new HashMap<>();
        Map<String,Object> parameterMap=new HashMap<>();
        if(type.equals("1")){/** 管理员验证用户申请加入群组 */
        parameterMap.put("userName",userName);//自定义账号或通讯账号
        parameterMap.put("groupId",groupId);//群组ID
        parameterMap.put("asker",asker);//申请成员的帐号
        parameterMap.put("confirm",confirm);//0 ：通过 1：拒绝
        map=GroupUtils.AskJoin(parameterMap);
          if(!"000000".equals(map.get("statusCode"))){
              return null;
          }else{
              if("0".equals(confirm)){
                  groupService.insertGroupUser(Integer.valueOf(asker),groupId);
              }else{
                  return null;
              }
          }
      }else if(type.equals("2")){/** 用户验证管理员邀请加入群组 */
          parameterMap.put("userName",userName);//自定义账号或通讯账号
          parameterMap.put("groupId",groupId);//	群组ID
          parameterMap.put("confirm",confirm);//0 ：通过 1：拒绝
          map=GroupUtils.InviteGroup(parameterMap);
          if(!"000000".equals(map.get("statusCode"))){
              throw new SystemException(Constant.ERR_GROUP);
          }else{
              if("0".equals(confirm)){
                  groupService.insertGroupUser(Integer.valueOf(userName),groupId);
              }else{
                  return null;
              }
          }
      }else if(type.equals("3")){/** 群组管理员删除成员 */
          parameterMap.put("userName",userName);//自定义账号或通讯账号
          parameterMap.put("groupId",groupId);//	群组ID
          parameterMap.put("members",members.split(","));//待删除成员
          map=GroupUtils.DeleteGroupMember(parameterMap);
          if(!"000000".equals(map.get("statusCode"))){
              throw new SystemException(Constant.ERR_GROUP);
          }else{
              groupService.delGroupRen(Integer.valueOf(members),groupId);
          }
      }else if(type.equals("4")){/** 成员主动退出群组 */
          parameterMap.put("userName",userName);//自定义账号或通讯账号
          parameterMap.put("groupId",groupId);//群组ID
          map=GroupUtils.LogoutGroup(parameterMap);
          if(!"000000".equals(map.get("statusCode"))){
              throw new SystemException(Constant.ERR_GROUP);
          }else{
              groupService.delGroupRen(Integer.valueOf(userName),groupId);
          }
      }
        return map;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
