package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Group;
import com.ef.golf.service.GroupService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.GroupUtils;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 查询群组属性
 * userName	String	可选	自定义账号或通讯账号
 * groupId	String	必选	群组ID
 * <p>
 * <p>
 * <p>
 * 返回
 * <p>
 * statusCode	String	必选	请求状态码，取值000000（成功）。
 * name	String	必选	群组名字，最长为50个字符
 * owner	String	必选	群组所有者（默认为管理员）
 * declared	String	必选	群组公告，最长为200个字符
 * count	String	必选	群组成员人数
 * dateCreated	String	必选	群组创建时间，格式为时间戳
 * permission
 * String	必选	申请加入模式
 * 0：默认直接加入
 * 1：需要身份验证
 * target
 * String
 * 必选
 * 群组类型
 * 0：讨论组
 * 1：群组
 * groupDomain
 * String
 * 可选
 * 用户扩展字段
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryGroupDetailAction extends AbstractService {

    private String userName;

    private String uid;
    private String sid;

    private String groupId;
    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        String url = "";
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        parameterMap.put("userName", userId + "");

        parameterMap.put("groupId", groupId);
        //调用创建群的容联云接口
        long begin = System.currentTimeMillis();
        Map<String, Object> map = GroupUtils.queryGroupDetail(parameterMap);
        System.out.println("============================" + (System.currentTimeMillis() - begin));
        try {
            //根据返回状态
            System.out.print(map.get("statusCode").equals("000000") + "" + (String) map.get("groupId"));
            if (map.get("statusCode").equals("000000")) {
                //构造群对象
                //public Group(String qunName, Integer userId, String permission, String declared, Date groupCreateTime, int del, Integer createUser, Integer modifyUser)
                //  Group group=new Group((String) map.get("groupId"),qiuHuiId,name,Integer.parseInt(userName),permission,declared,new Date(),new Date(),0,userId,null);
                //成功进行数据库写入  写入群表  写入成员与群的关系表
                //  groupService.createGroup(group);
                String id = (String) map.get("owner");
                MineVo mineVo = userService.getInfo(Integer.valueOf(id));
                returnMap.put("map", map);//群信息
                /*returnMap.put("groupId",groupId);*/
                map.put("headPortraitUrl", mineVo.getHeadPortraitUrl());
                map.put("status", "1");
                map.put("groupId", groupId);
                Boolean hasIn = true;
                List<Integer> rens = groupService.getGroupRen(groupId);
                if (rens.contains(Integer.valueOf(userId))) {
                    map.put("hasIn", hasIn);
                } else {
                    hasIn = false;
                    map.put("hasIn", hasIn);
                }
                /*returnMap.put("headImg",mineVo.getHeadPortraitUrl());
                returnMap.put("status","1");//查询成功*/
            } else {
                throw new SystemException(Constant.ERR_QUERYGROUP);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
