package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.util.GroupUtils;
import com.ef.golf.util.ServicePushUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 管理员验证用户申请加入群组
 * <p>
 * 请求
 * userName	String	可选	自定义账号或通讯账号
 * groupId	String	必选	群组ID
 * asker	String	必选	申请成员的帐号
 * confirm	String	可选	0 ：通过 1：拒绝
 * <p>
 * <p>
 * 返回
 * statusCode	String	必选	请求状态码，取值000000（成功），可参考Rest 错误代码。
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AskJoinAction extends AbstractService {


    private String userName;
    private String groupId;
    private String asker;
    private String confirm;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        String url = "";

        parameterMap.put("userName", userName);
        parameterMap.put("groupId", groupId);

        parameterMap.put("asker", asker);
        parameterMap.put("confirm", confirm);

        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.joinGroup(parameterMap);
        try {
            //根据返回状态
            System.out.print(map.get("statusCode").equals("000000") + "" + (String) map.get("groupId"));
            if (map.get("statusCode").equals("000000")) {
                //人群关系表加入信息

                returnMap.put("status", "1");//代表申请加群请求发出成功


                //根据confirm的值 推送消息给申请人
                Map<String, Object> mapPush = new HashMap<>();
                map.put("message", "欢迎");
                ServicePushUtils.servicePush("26", "26", new String[]{userName}, "群主同意您加入", mapPush);
            } else {
                returnMap.put("status", "0");//代表申请加群请求发出失败
            }
        } catch (Exception ex) {
            returnMap.put("status", "0");//代表申请加群请求发出失败
            ex.printStackTrace();
        }
        return returnMap;
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

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
