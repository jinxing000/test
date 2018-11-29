package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Group;
import com.ef.golf.service.GroupService;
import com.ef.golf.util.GroupUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 创建群组
 * userName	String	必选	自定义账号或通讯账号
 * name	String	必选	群组名字，最长为50个字符
 * type	String	必选	群组类型
 * 0：临时组(上限100人)
 * 1：付费普通组(上限300人)
 * 2：付费普通组(上限500人)
 * 3：付费普通组 (上限1000人)
 * 4：付费VIP组（上限2000人）
 * 注意：讨论组取值范围0、1、2，如果大于2则默认2
 * permission	String	可选
 * 申请加入模式
 * 0：默认直接加入
 * 1：需要身份验证
 * 2：私有群组    缺省0
 * declared
 * String	可选	群组公告，最长为200个字符
 * target
 * String
 * 可选
 * 0：讨论组
 * 1：群组  缺省1
 * groupDomain
 * String
 * 可选
 * 用户扩展字段
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateGroupAction extends AbstractService {

    @Resource
    private GroupService groupService;

    //当前用户
    private Integer userId;

    //群所属球会Id
    private Integer qiuHuiId;

    //创建群需要的参数
    private String userName;//必选	自定义账号或通讯账号  群主id
    private String name;//必选	群组名字，最长为50个字符
    private String type;    //	必选	群组类型

    private String permission;    //	可选 申请加入模式

    private String declared;//可选	群组公告，最长为200个字符
    private String target;

    private String groupDomain = "";


    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        String url = "";

        parameterMap.put("userName", userName);
        parameterMap.put("name", name);
        parameterMap.put("type", type);
        parameterMap.put("permission", permission);
        parameterMap.put("declared", declared);
        parameterMap.put("target", target);
        parameterMap.put("groupDomain", groupDomain);
        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.createGroup(parameterMap);
        try {
            //根据返回状态
            System.out.print(map.get("statusCode").equals("000000") + "" + (String) map.get("groupId"));
            if (map.get("statusCode").equals("000000")) {
                //构造群对象
                //public Group(String qunName, Integer userId, String permission, String declared, Date groupCreateTime, int del, Integer createUser, Integer modifyUser)
                Group group = new Group((String) map.get("groupId"), Integer.parseInt(userName), name, Integer.parseInt(userName), permission, declared, new Date(), new Date(), 1, userId, null);
                //成功进行数据库写入  写入群表  写入成员与群的关系表
                groupService.insertSelective(group);
                returnMap.put("status", "1");//代表创建群成功
            } else {
                returnMap.put("status", "0");//代表创建群失败
            }
        } catch (Exception ex) {
            returnMap.put("status", "0");//数据库操作失败
            ex.printStackTrace();
        }
        return returnMap;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(Integer qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDeclared() {
        return declared;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getGroupDomain() {
        return groupDomain;
    }

    public void setGroupDomain(String groupDomain) {
        this.groupDomain = groupDomain;
    }
}
