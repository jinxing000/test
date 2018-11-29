/*
 * UrlUtils.java       1.0    2018年3月21日
 *
 * Copyright (c) 2011, 2014 Tianjin YiDianFu Network Technology Co. Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * YiDianFu Network Technology Co. Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with YiDianFu.
 */
package com.ef.golf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloopen.rest.sdk.utils.CcopHttpClient;
import com.cloopen.rest.sdk.utils.EncryptUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import ytx.org.apache.http.Header;
import ytx.org.apache.http.client.methods.HttpRequestBase;
import ytx.org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author 张宁 2018年3月21日 上午9:30:26
 * @version 1.0 推送
 */
public class GroupUrlUtils {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapJson(String url, JSONObject jsonObject) {
        Map<String, Object> hashMap = new HashMap<String, Object>();

        String date = DateUtil.getNewDate();
        String sig = Constants.APPID + Constants.TOKEN + date;
        String encode = MD5.encode(sig);
        String Authorization = Base64.getBase64(Constants.APPID + ":" + date);
         /* POST /2013-12-26/Application/20150314000000110000000000000010/IM/PushMsg?sig=C1F20E7A97  示例*//*
               /* /{SoftVersion}/Application/{appId}/IM/PushMsg*/
        String httpsUrl = Constants.BASEURL + Constants.APPID + url + "?sig=" + encode;// "/IM/Group/CreateGroup?sig="
        System.out.println(httpsUrl);
        HttpPost post = new HttpPost(httpsUrl);
        post.setHeader("Accept", "application/json");
        // post.setHeader("HTTP", "/1.1");
        post.setHeader("Content-Type", "application/json;charset=utf-8");
        post.setHeader("Authorization", Authorization);
        /*
         * JSONObject jsonObject = new JSONObject(); jsonObject.put("userName", "23"); jsonObject.put("name", "亿方电子商务");
         * jsonObject.put("type", "0"); jsonObject.put("declared", "亿方电子商务"); jsonObject.put("permission", "0");
         */
        BasicHttpEntity httpEntity = new BasicHttpEntity();

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonObject.toString()
                    .getBytes("UTF-8"));
            httpEntity.setContent(byteArrayInputStream);
            httpEntity.setContentLength(jsonObject.toString().getBytes("UTF-8").length);
            post.setEntity(httpEntity);
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                hashMap= (Map<String, Object>) JSON.parse(str);

               /* if (maps.get("statusCode").equals("000000")) {
                  *//*  hashMap.put("msg", "群创建成功");*//*
                    hashMap.put("code", "1");
             *//*       hashMap.put("maps", maps);*//*
                } else {
                   *//* hashMap.put("msg", "推送成功,返回状态码异常");*//*
                    hashMap.put("code", "2");
                    *//*hashMap.put("statusCode", maps.get("statusCode"));*//*
                }*/
            }
        } catch (Exception e) {
            // e.printStackTrace();
            LoggerUtils.error(GroupUrlUtils.class, "群处理失败！", e);
        }
        return hashMap;
    }


   /* @SuppressWarnings("unchecked"*/
    /*public static Map<String, Object> getMapJson(String url, JSONObject jsonObject) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> hashMap = new HashMap<String, Object>();

        String date = DateUtil.getNewDate();
        String timestamp = com.cloopen.rest.sdk.utils.DateUtil.dateToStr(new Date(), 5);

        String Authorization = Base64.getBase64(Constants.APPID + ":" + date);
        EncryptUtil eu=new EncryptUtil();
       *//* String sig = "";
        sig = Constants.APPID+ Constants.TOKEN + timestamp;
        *//**//*String  encode= MD5.encode(sig);*//**//*



        String encode = eu.md5Digest(sig);*//*

        String sig = Constants.APPID + Constants.TOKEN + date;
        System.out.println(sig);
        String encode = MD5.encode(sig);

         *//* POST /2013-12-26/Application/20150314000000110000000000000010/IM/PushMsg?sig=C1F20E7A97  示例*//*
               *//* /{SoftVersion}/Application/{appId}/IM/PushMsg*//*
        String httpsUrl = Constants.BASEURL + Constants.APPID + url + "?sig=" + encode;// "/IM/Group/CreateGroup?sig="
        System.out.println(httpsUrl);
        HttpRequestBase mHttpRequestBase = null;
        mHttpRequestBase = new ytx.org.apache.http.client.methods.HttpPost(httpsUrl);
        mHttpRequestBase.setHeader("Accept", "application/json");
        mHttpRequestBase.setHeader("Content-Type", "application/json;charset=utf-8");
        String src = Constants.APPID + ":" + timestamp;
        String auth = eu.base64Encoder(src);
        ((HttpRequestBase)mHttpRequestBase).setHeader("Authorization", auth);
        ((HttpRequestBase)mHttpRequestBase).setHeader("Connection", "keep-alive");
        ytx.org.apache.http.client.methods.HttpPost post = (ytx.org.apache.http.client.methods.HttpPost)mHttpRequestBase;


        ytx.org.apache.http.entity.BasicHttpEntity httpEntity = new ytx.org.apache.http.entity.BasicHttpEntity();

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonObject.toString()
                    .getBytes("UTF-8"));
            httpEntity.setContent(byteArrayInputStream);
            httpEntity.setContentLength(jsonObject.toString().getBytes("UTF-8").length);
            post.setEntity(httpEntity);
            CcopHttpClient chc = new CcopHttpClient();
            DefaultHttpClient httpclient = null;


                httpclient = chc.registerSSL("imapp.yuntongxun.com", "TLS", 8883, "https");
            *//*ytx.org.apache.http.client.HttpClient httpclient = HttpClientBuilder.create().build();*//*
           Header[] headers= post.getAllHeaders();
           for(int i=0;i<headers.length;i++){
               System.out.println(headers[i].getName()+""+headers[i].getValue());
           }
            ytx.org.apache.http.HttpResponse response = httpclient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                ytx.org.apache.http.HttpEntity entity = response.getEntity();
                String str = ytx.org.apache.http.util.EntityUtils.toString(entity);
                Map<String, Object> maps = (Map<String, Object>) JSON.parse(str);

                if (maps.get("statusCode").equals("000000")) {
                    hashMap.put("msg", "推送成功");
                    hashMap.put("code", "1");
                    hashMap.put("maps", maps);
                    hashMap.put("httpsUrl",httpsUrl);
                } else {
                    hashMap.put("msg", "推送成功,返回状态码异常");
                    hashMap.put("code", "2");
                    hashMap.put("statusCode", maps.get("statusCode"));
                }

            } else {
                hashMap.put("msg", "推送失败");
                hashMap.put("code", "0");
            }


        } catch (Exception e) {
            // e.printStackTrace();
            LoggerUtils.error(UrlUtils.class, "推送失败！", e);
        }

        return hashMap;
    }*/

}
