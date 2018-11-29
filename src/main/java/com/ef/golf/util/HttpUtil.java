package com.ef.golf.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * com.ef.golf.test
 * Administrator
 * 2018/9/3 13:39
 */
public class HttpUtil {


    //连接池
    private static PoolingHttpClientConnectionManager connectionMgr;
    //超时时间
    private static final int MAX_TIMEOUT = 7000;

    private static RequestConfig requestConfig;
    static{
        //设置连接池
        connectionMgr = new PoolingHttpClientConnectionManager();
        //设置连接池大小
        connectionMgr.setMaxTotal(100);
        connectionMgr.setDefaultMaxPerRoute(connectionMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        //设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        //设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        //设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();

    }

    /**
     * GET 请求       带输入参数
     * @param Url      请求host地址
     * @param params   参数
     * @return
     */
    public static String httpGet(String Url,Map<String, Object>params)
    {
        //返回结果
        String result = null;
        //拼接url
        StringBuilder builder = new StringBuilder(Url);
        if (Url.contains("?")) {
            builder.append("&");
        }else{
            builder.append("?");
        }
        int i=0;
        for (String key : params.keySet()) {
            if (i != 0 ) {
                builder.append("&");
            }
            builder.append(key);
            builder.append("=");
            builder.append(params.get(key));

            i++;
        }
        String apiUrl = builder.toString();
        //创建client
        HttpClient client = HttpClients.createDefault();

        try {
            HttpGet get = new HttpGet(apiUrl);
            HttpResponse response = client.execute(get);
            //获取请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity,"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST 请求
     * @param url             请求url
     * @param params          post提交参数
     * @return
     */
    public static String httpPost(String url,Map<String, Object>params)
    {
        HttpClient client = HttpClients.createDefault();
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            //添加post提交参数
            ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();

            for(Map.Entry<String, Object> entry:params.entrySet())
            {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            post.setEntity(new UrlEncodedFormEntity(pairList,"UTF-8"));

            HttpResponse response = client.execute(post);
            //获取状态码
            int statueCode = response.getStatusLine().getStatusCode();
            System.out.println(statueCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @Test //发券规则
    public void Test4() throws ParseException {
       int year = 2018;
        int month =  9;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DATE));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = "2018-10-03 08:30";
        Date date = simpleDateFormat.parse(s);
        System.out.println(simpleDateFormat.format(date));
        System.out.println(date.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -24);// 24小时制
        date = cal.getTime();
        System.out.println(simpleDateFormat.format(date));
        System.out.println(date.getTime());
        if(new Date().getTime()<date.getTime()){
            System.out.println("lalala");
        }
        /*double scale = 8.5;
        double shop = 15300.00;
        double jiage = shop*(scale/100.0);
        System.out.println(jiage);
        //品牌规则 购物满1000送一张200满减券 满500送50满减券 满100送20满减券  现有一单2688元 实现分配三种券
        int yi = (int)(jiage/1000);
        int er = (int)(jiage%1000/500);
        int san = (int)(jiage%1000%500/100);
        System.out.println("一级券="+yi);
        System.out.println("二级券="+er);
        System.out.println("三级券="+san);
        int  quanshu=0;
        int yu=(int)jiage;
        for (int i=0;i<4;i++){
            yu=yu%100;
            quanshu=yu/100;
            list.add()
        }*/
    }

}
