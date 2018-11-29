package com.ef.golf.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.util.List;
import java.util.Map;

/**
 * userName	String	必选	当前个人账号
 * type	String	必选	1:设置免打扰 2:取消免打扰
 * setAccount	String	必选	设置的账号，个人账号或者群组ID
 * <p>
 * userName	String	必选	当前个人账号
 * pageNo	String	可选	页码，缺省是1
 * pageSize	String	可选	分页记录数 缺省是100
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DisturbAction extends AbstractService {

    //创建群需要的参数
    private String userName;//必选 当前个人账号
    private String pageNo;
    private String pageSize;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userName", userName);
        parameterMap.put("pageNo", pageNo);
        parameterMap.put("pageSize", pageSize);
        //调用创建群的容联云接口
        Map<String, Object> map = GroupUtils.GetDisturb(parameterMap);
        JSONArray jsonArray = (JSONArray) map.get("result");
        List<String> list = JSONObject.parseArray(JSONObject.toJSONString(jsonArray), String.class);
        /** statusCode	String	必选	请求状态码，取值000000（成功）
         totalSize	int	必选	总记录数
         result	List	必选	结果集 */
        try {
            if (map.get("statusCode").equals("000000")) {

            } else {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnMap;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
