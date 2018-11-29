package com.ef.golf.util;

import com.alibaba.fastjson.JSONObject;
/**
 *  ━━━━━━神兽出没━━━━━━
 *　　　┏┓　　　┏┓
 *　　┏┛┻━━━┛┻┓
 *　  ┃　　　　　　　┃
 *　  ┃　　　━　　　┃
 *　  ┃　┳┛　┗┳  ┃
 *　　┃　　　　　　　┃
 *　　┃　　　┻　　　┃
 *　　┃　　　　　　　┃
 *　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 *　　　　┃　　　┃    神兽保佑,代码无bug
 *　　　　┃　　　┃
 *　　　　┃　　　┗━━━┓
 *　　　　┃　　　　　　　┣┓
 *　　　　┃　　　　　　　┏┛
 *　　　　┗┓┓┏━┳┓┏┛
 *　　　　　┃┫┫　┃┫┫
 *　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━
 */
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
//服务推送
public class ServicePushUtils {

    public static Map<String, Object> servicePush(String sender,String msgType,String[] receiver,String msgContent,Map<String,Object>map){
            //容联云url
            String url = "/IM/PushMsg";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pushType","1");//推送类型，1：个人，2：群组，默认为1
            jsonObject.put("sender",sender);//发送者帐号
            jsonObject.put("senderNickName","aaaa");//发送者帐号
            jsonObject.put("receiver",receiver);//接收者帐号
            jsonObject.put("msgType",msgType);//消息类型 26：指令消息
            jsonObject.put("msgContent",msgContent);//可选 文本内容s
            jsonObject.put("msgDomain","aa");//可选 扩展字段
            jsonObject.put("msgFileName","aa");//可选 文件名
            jsonObject.put("msgFileUrl","aaa");//可选 文件绝对路径
                if(null!=map) {
                        Map<String, Object> senderInfo = new HashMap<>();//人发自定义消息体
                        Map<String, Object> groupInfo = new HashMap<>();//群发自定义消息体
                        Map<String, Object> apsalert = new HashMap<>();//自定义消息map
                        Map<String, Object> extOpts = new HashMap<>();//推送map
                        extOpts.put("isSave", "2");
                        extOpts.put("isOfflinePush", "2");
                        extOpts.put("isHint", "1");
                        extOpts.put("isSyncMsg", "2");
                        if ("1".equals((String) map.get("type"))) {/** 点对点 */
                                senderInfo.put("userId", (String) map.get("userId"));
                                senderInfo.put("headPortraitUrl", (String) map.get("headPortraitUrl"));
                                senderInfo.put("nickName", (String) map.get("nickName"));
                                apsalert.put("senderInfo", senderInfo);
                        } else if ("2".equals((String) map.get("type"))) {/** 群 */
                                groupInfo.put("groupId", (String) map.get("groupId"));
                                groupInfo.put("headPortraitUrl", (String) map.get("GheadPortraitUrl"));
                                groupInfo.put("nickName", (String) map.get("nickName"));
                                groupInfo.put("owner", (String) map.get("owner"));
                                apsalert.put("groupInfo", groupInfo);
                        } else if ("3".equals((String) map.get("type"))) { /** 所有 */
                                senderInfo.put("userId", (String) map.get("userId"));
                                senderInfo.put("headPortraitUrl", (String) map.get("headPortraitUrl"));
                                senderInfo.put("nickName", (String) map.get("nickName"));

                                apsalert.put("senderInfo", senderInfo);

                                groupInfo.put("groupId", (String) map.get("groupId"));
                                groupInfo.put("headPortraitUrl", (String) map.get("GheadPortraitUrl"));
                                groupInfo.put("name", (String) map.get("name"));
                                groupInfo.put("owner", (String) map.get("owner"));

                                apsalert.put("groupInfo", groupInfo);
                        }
                        extOpts.put("apsalert", apsalert);
                        jsonObject.put("extOpts",Base64.getBase64(extOpts.toString()));//自定义消息推送内容
                }
            /** 开始推送 */
            Map<String, Object> hashMap = UrlUtils.getMapJson(url,jsonObject);
            return hashMap;
    }
}
