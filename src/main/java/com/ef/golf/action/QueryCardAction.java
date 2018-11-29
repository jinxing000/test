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
 * 查询群名片信息
 * userName	String	可选	自定义账号或通讯账号
 * other	String	必选	群组中成员的账号
 * belong	String	必选	用户所属的群组ID
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryCardAction extends AbstractService {
    private String userName;
    private String other;
    private String belong;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userName", userName);
        parameterMap.put("other", other);
        parameterMap.put("belong", belong);

        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.createGroup(parameterMap);
        try {
            //根据返回状态
            System.out.print(map.get("statusCode").equals("000000") + "" + (String) map.get("groupId"));
            if (map.get("statusCode").equals("000000")) {
                throw new SystemException(Constant.ERR_QUERYGROUP);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SystemException(Constant.ERR_QUERYGROUP);
        }
        return parameterMap;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
