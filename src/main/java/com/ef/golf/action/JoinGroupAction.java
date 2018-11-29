package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Group;
import com.ef.golf.util.GroupUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 用户申请加入群组
 * <p>
 * 请求
 * userName	String	可选	自定义账号或通讯账号
 * groupId	String	必选	群组ID
 * declared	String	可选	申请理由，最长为50个字符
 * <p>
 * 返回
 * <p>
 * statusCode	String	必选	请求状态码，取值000000（成功）。
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JoinGroupAction extends AbstractService {

    private String userName;

    private String groupId;

    private String declared;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        String url = "";

        parameterMap.put("userName", userName);
        parameterMap.put("groupId", groupId);
        parameterMap.put("declared", declared);
        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.joinGroup(parameterMap);
        try {
            //根据返回状态
            System.out.print(map.get("statusCode").equals("000000") + "" + (String) map.get("groupId"));
            if (map.get("statusCode").equals("000000")) {
                //人群关系表加入信息

                returnMap.put("status", "1");//代表申请加群请求发出成功
                //调用QueryGroupDetail 查询该群组的管理员账号

                //推送消息给群主
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

    public String getDeclared() {
        return declared;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }
}
