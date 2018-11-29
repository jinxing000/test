package com.ef.golf.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */
public class GroupUtils {
    public static Map<String, Object> createGroup(Map<String,Object> map){
        //容联云url
        String url = "/IM/Group/CreateGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("name",map.get("name"));//群组名字，最长为50个字符
        jsonObject.put("type",map.get("type"));//群组类型 0：临时组(上限100人) 1：付费普通组(上限300人) 2：付费普通组(上限500人)  3：付费普通组 (上限1000人)  4：付费VIP组（上限2000人）         注意：讨论组取值范围0、1、2，如果大于2则默认2
        jsonObject.put("permission",map.get("permission"));//申请加入模式 0：默认直接加入 1：需要身份验证2：私有群组    缺省0
        jsonObject.put("declared",map.get("declared"));//群组公告，最长为200个字符
        jsonObject.put("target",map.get("target"));//0：讨论组  1：群组  缺省1
        jsonObject.put("groupDomain",Base64.getBase64(map.get("groupDomain").toString()));//用户扩展字段

        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 修改群属性
     * @param map
     * @return
     */
    public static Map<String, Object> ModifyGroup(Map<String,Object> map){
        /**userName	String	必选	自定义账号或通讯账号
         groupId	String	必选	群组ID
         permission	String	可选 申请加入模式 0：默认直接加入 1：需要身份验证 2：私有群组    缺省0
         name	String	必选 群组名字，最长50个字符
         declared	String	可选	群组公告，最长为200个字符
         groupDomain String 可选
         */
        //容联云url
        String url = "/IM/Group/ModifyGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//必选	自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//必选	群组ID
        jsonObject.put("permission",map.get("permission"));//可选 申请加入模式 0：默认直接加入 1：需要身份验证 2：私有群组    缺省0
        jsonObject.put("name",map.get("name"));//必选 群组名字，最长50个字符
        jsonObject.put("declared",map.get("declared"));//可选	群组公告，最长为200个字符
        jsonObject.put("groupDomain",map.get("groupDomain"));//用户扩展字段
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 群查询
     * @param map
     * @return
     */
    public static Map<String, Object> queryGroupDetail(Map<String,Object> map){
        //容联云url
        String url = "/IM/Group/QueryGroupDetail";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号

        jsonObject.put("groupId",map.get("groupId"));//用户扩展字段

        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 删除群组
     * @param map
     * @return
     */
    public static Map<String, Object> DeleteGroup(Map<String,Object> map){
        //容联云url
        String url = "/IM/Group/DeleteGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//群组ID
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 查询成员所加入的群
     * @param map
     * @return
     */
    public static Map<String, Object> queryGroup(Map<String,Object> map){
        //容联云url
        String url = "/IM/Member/QueryGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("target",map.get("target"));//用户扩展字段
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /**
     * 查询群名片信息
     * @param map
     * @return
     */
    public static Map<String, Object> queryCard(Map<String,Object> map){
        //容联云url
        String url = "/IM/Member/QueryCard";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("other",map.get("other"));//群组中成员的账号
        jsonObject.put("belong",map.get("belong"));//用户所属的群组ID
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /**
     * 查询群成员
     * @param map
     * @return
     */
    public static Map<String, Object> QueryMember(Map<String,Object> map){
        //容联云url
        String url = "/IM/Member/QueryMember";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("other",map.get("other"));//群组中成员的账号
        jsonObject.put("belong",map.get("belong"));//用户所属的群组ID
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 群组管理员邀请用户加入群组
     */
    public static Map<String, Object> InviteJoinGroup(Map<String,Object> map){
        /**
         * userName	String	 必选	自定义账号或通讯账号
         groupId	String	必选	群组ID
         members	list
         必选	被邀请成员，一次最多可以邀请50人，且邀请的成员必须是已经在客户端登陆过的用户。
         confirm	String	可选
         是否需要被邀请人确认
         0 ：需要
         1：不需要（自动加入群组）缺省1
         declared	String	可选	邀请理由，最长为50个字符
         * */
        //容联云url
        String url = "/IM/Group/InviteJoinGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//必选	自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//必选	群组ID
        jsonObject.put("members",map.get("members"));//必选	被邀请成员，一次最多可以邀请50人，且邀请的成员必须是已经在客户端登陆过的用户。
        jsonObject.put("confirm",map.get("confirm"));//可选 是否需要被邀请人确认  0 ：需要 1：不需要（自动加入群组）缺省1
        jsonObject.put("declared",map.get("declared"));//可选	邀请理由，最长为50个字符
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    /**
     * 管理员验证用户申请加入群组
     */
    public static Map<String, Object> AskJoin(Map<String,Object> map){
        //容联云url
        String url = "/IM/Member/AskJoin";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//群组ID
        jsonObject.put("asker",map.get("asker"));//申请成员的帐号
        jsonObject.put("confirm",map.get("confirm"));//0 ：通过 1：拒绝
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /**
     * 用户验证管理员邀请加入群组
     */
    public static Map<String, Object> InviteGroup(Map<String,Object> map){
        //容联云url
        String url = "/IM/Member/InviteGroup";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//	群组ID
        jsonObject.put("confirm",map.get("confirm"));//0 ：通过 1：拒绝
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /**
     * 群组管理员删除成员
     */
    public static Map<String, Object> DeleteGroupMember(Map<String,Object> map){
        //容联云url
        String url = "/IM/Group/DeleteGroupMember";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//	群组ID
        jsonObject.put("members",map.get("members"));//待删除成员
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /**
     * 成员主动退出群组
     */
    public static Map<String, Object> LogoutGroup(Map<String,Object> map){
        //容联云url
        String url = "/IM/Group/LogoutGroup";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("groupId",map.get("groupId"));//	群组ID
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /** 设置免打扰 */
    public static Map<String, Object> SetDisturb(Map<String,Object> map){
        //容联云url
        String url = "/IM/SetDisturb";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userName",map.get("userName"));//当前个人账号
        jsonObject.put("type",map.get("type"));//1:设置免打扰 2:取消免打扰
        jsonObject.put("setAccount",map.get("setAccount"));//设置的账号，个人账号或者群组ID
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /** 获取免打扰列表 */
    public static Map<String, Object> GetDisturb(Map<String,Object> map){
        //容联云url
        String url = "/IM/SetDisturb";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//当前个人账号
        jsonObject.put("pageNo",map.get("pageNo"));
        jsonObject.put("pageSize",map.get("pageSize"));
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
    /** 设置群组成员角色 */
    public static Map<String, Object> SetMemberRole(Map<String,Object> map){
        //容联云url
        String url = "/IM/SetDisturb";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//当前个人账号
        jsonObject.put("pageNo",map.get("pageNo"));
        jsonObject.put("pageSize",map.get("pageSize"));
        return GroupUrlUtils.getMapJson(url,jsonObject);
    }

    public static Map<String,Object> joinGroup(Map<String, Object> map) {
        //容联云url
        String url = "/IM/Group/JoinGroup";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",map.get("userName"));//自定义账号或通讯账号
        jsonObject.put("declared",map.get("declared"));//群组公告，最长为200个字符
        jsonObject.put("groupId","groupId");//群Id

        return GroupUrlUtils.getMapJson(url,jsonObject);
    }
}
