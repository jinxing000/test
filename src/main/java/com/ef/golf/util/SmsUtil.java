package com.ef.golf.util;


import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.cloopen.rest.sdk.utils.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * create by xzw
 * 发送手机验证码工具类
 *
 */
public class SmsUtil {

    //服务器
    private static String servicePort;
    //主账号
    private static String mainAccount;
    //主账号令牌
    private static String mainToken;
    //应用ID
    private static String appID;
    //模板ID
    private static String templateID;
    //失效时间
    private static String outTime;

    /**
     *
     * @param phoneNumber 发送的电话号码
     * @param code 验证码
     * @return true 发送成功 false 发送失败
     */
    public static boolean sendMessage(String phoneNumber,String code){

        Map<String,Object> result=null;
        boolean end=false;
        //初始化SDK

        CCPRestSmsSDK resultApi=new CCPRestSmsSDK();
        //初始化服务器和端口
            //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
            //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");
        resultApi.init(servicePort,"8883");
        //初始化主账号和令牌
        resultApi.setAccount(mainAccount,mainToken);
        //初始化应用ID
        resultApi.setAppId(appID);
        //开始发送信息
        result=resultApi.sendTemplateSMS(phoneNumber,templateID,new String[]{code,outTime});
        if ("000000".equals(result.get("statusCode"))){
            end=true;
        }
        return end;
    }

    public static boolean sendSMS(String phoneNumber,String templateId){
        Map<String,Object> result=null;
        boolean end=false;
        //初始化SDK
        CCPRestSmsSDK resultApi=new CCPRestSmsSDK();
        //初始化服务器和端口
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");
        resultApi.init(servicePort,"8883");
        //初始化主账号和令牌
        resultApi.setAccount(mainAccount,mainToken);
        //初始化应用ID
        resultApi.setAppId(appID);
        //开始发送信息
        result=resultApi.sendTemplateSMS(phoneNumber,templateId,null);
        if ("000000".equals(result.get("statusCode"))){
            end=true;
        }
        return end;
    }
    public static boolean sendSMS(String phoneNumber,String templateId,String[] datas){
        Map<String,Object> result=null;
        boolean end=false;
        //初始化SDK
        CCPRestSmsSDK resultApi=new CCPRestSmsSDK();
        //初始化服务器和端口
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");
        resultApi.init(servicePort,"8883");
        //初始化主账号和令牌
        resultApi.setAccount(mainAccount,mainToken);
        //初始化应用ID
        resultApi.setAppId(appID);
        //开始发送信息
        result=resultApi.sendTemplateSMS(phoneNumber,templateId,datas);
        if ("000000".equals(result.get("statusCode"))){
            end=true;
        }
        return end;
    }


    /**
     *容联云创建子账户
     * @param userId ：账号
     * @return
     * https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85f341b69015f48393bdb0724/SubAccounts?sig=17E6DF228B0D888FA927C3BA2025B2EE
     */
    public static boolean createUser(String userId){

        Map<String, Object> result = null;

        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init(servicePort, "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.setAccount(mainAccount, mainToken);// 初始化主帐号和主帐号TOKEN
        restAPI.setAppId(appID);// 初始化应用ID
        result = restAPI.createSubAccount(userId);
        System.out.println("SDKTestCreateSubAccount result=" + result);
        boolean flag=false;
        if("000000".equals(result.get("statusCode"))){
            System.out.println("账号创建成功");
            flag=true;
            //正常返回输出data包体信息（map）
            Map<String,Object> data = (Map<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        return flag;

    }


    static {

        smsInit();

    }

    /**
     *初始化发送短息的配置信息
     */
    private static void smsInit(){

        Properties properties=new Properties();

        //加载发送验证短信的配置信息
        InputStream in= PropertiesUtil.class.getClassLoader().getResourceAsStream("sms.properties");

        try {
            properties.load(in);
            //获取服务器
            servicePort=properties.getProperty("servicePort");
            //获取主账号
            mainAccount=properties.getProperty("mainAccount");
            //获取主账号令牌
            mainToken=properties.getProperty("mainToken");
            //获取应用ID
            appID=properties.getProperty("appID");
            //获取模板ID
            templateID=properties.getProperty("templateID");
            //获取验证码失效时间
            outTime=properties.getProperty("outTime");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
