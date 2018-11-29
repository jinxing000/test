package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
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
 * 删除群
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeleteGroupAction extends AbstractService {


    //用户
    private String groupId;
    //创建群需要的参数
    private String userName;//必选	自定义账号或通讯账号  群主id


    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("userName", userName);//自定义账号或通讯账号
        parameterMap.put("groupId", groupId);//群组ID
        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.DeleteGroup(parameterMap);
        try {
            //根据返回状态
            if (map.get("statusCode").equals("000000")) {
                returnMap.put("status", "0");//代表创建群成功
                returnMap.put("message", "删除成功");
            } else {
                throw new SystemException(Constant.ERR_GROUP);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SystemException(Constant.ERR_GROUP);
        }
        return returnMap;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
