package com.ef.golf.common;

import com.ef.golf.util.ServiceBeanUtil;

import javax.xml.ws.Service;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/1/12.
 * Date: 2017/1/12 15:31
 */
public class Consts {



    public static Map RET_CODE = (Map)ServiceBeanUtil.getBean("retCode");

    public static String REDIS_OUT_TIME= (String) ServiceBeanUtil.getBean("redisOutTime");

    public static String REDIS_MOBLE_OUT_TIME=(String)ServiceBeanUtil.getBean("mobleRedisOutTime");

    public static String PXX_APP_ID= (String) ServiceBeanUtil.getBean("pxxAppId");
    public static String SUCCESS_URL= (String) ServiceBeanUtil.getBean("success_url");
    public static String CANCEL_URL= (String) ServiceBeanUtil.getBean("cancel_url");
    public static String PXX_API_KEY=(String)ServiceBeanUtil.getBean("PxxApiKey");
    public static String mobAppkey=(String)ServiceBeanUtil.getBean("mobAppkey");
    public static String mobAppSecret=(String)ServiceBeanUtil.getBean("mobAppSecret");


}
