package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.util.ServicePushUtils;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　  ┃　　　　　　　┃
 * 　  ┃　　　━　　　┃
 * 　  ┃　┳┛　┗┳  ┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ServicePushAction extends AbstractService {
    /*  @NotNull(message = "接收者id必需要!")

      private String xiaoxi;
      private String xiaoxileixing;*/
    private String[] receiver;
private String sender;
private String magType;


    @Override
    public Object doService() throws Exception {
      /* Map<String, Object> map = new HashMap<>();*/
        String[] ss = {"26"};
       Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = ServicePushUtils.servicePush(sender, magType, receiver, "xxx" + "抢了你的红包",map );
        /**
         pushWork.setAlias(alias);//设置推送别名集合[“alias1″,”alias2”]，target=2则必选
         pushWork.setTarget(target);//2//推送范围:1广播；2别名；3标签；4regid；5地理位置;6用户分群
         pushWork.setContent(content);//推送内容
         pushWork.setType(1);//推送类型：1通知；2自定义
         pushWork.setExtras(extras);
         * */
           /* Map<String,Object> map = new HashMap();
            map.put("title","标题");
            map.put("content","内容");
            map.put("url","ifungolf://workList");
            String push =  MobPushUtil.MobPush(2,"测试内容",receiver,"1", JSON.toJSONString(map));*/
        return map1;
    }

    public void setReceiver(String[] receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMagType(String magType) {
        this.magType = magType;
    }
}
