/*
 * AcountUtil.java       1.0    2018年6月6日
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

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 *
 * @author Administrator 2018年6月6日 上午9:18:32
 * @version 1.0
 */
public class AcountUtil {

    @SuppressWarnings({ "resource", "deprecation" })
    public static String createPingUser(Long userId,String url) {
        String strResult = "";
        String baseUrl ="http://localhost:8080/golf/ticket";
        HttpClient client = new DefaultHttpClient();
        // 发送post请求
        HttpPost request = new HttpPost(baseUrl/* + "?userId=" + userId*/);
        try {
            HttpResponse response = client.execute(request);
            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                strResult = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return strResult;
    }
/**
      * 从html中提取纯文本
      *
      * @author
      * @date: 2018年8月3日 上午9:14:27
      * @param strHtml
      * @return
      */
    public static String StripHT(String strHtml) {
    if(StringUtils.isBlank(strHtml)) {
    return "";
    }
    String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
    txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");// 去除字符串中的空格,回车,换行符,制表符
    return txtcontent;
    }
     /**
          * 从HTML中提取图片链接
          *
          * @author
          * @date: 2018年8月3日 上午9:08:09
          * @param content
          * @return
          */
    public static List<String> extractImg(String content) {
    List<String> srcList = new ArrayList<String>(); // 用来存储获取到的图片地址
    if(StringUtils.isBlank(content)) {
    return srcList;
    }
    Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");// 匹配字符串中的img标签
    Matcher matcher = p.matcher(content);
    boolean hasPic = matcher.find();
     if (hasPic == true)// 判断是否含有图片
     {
     while (hasPic) // 如果含有图片，那么持续进行查找，直到匹配不到
    {
    String group = matcher.group(2);// 获取第二个分组的内容，也就是 (.*?)匹配到的
     Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");// 匹配图片的地址
    Matcher matcher2 = srcText.matcher(group);
     if (matcher2.find()) {
    srcList.add(matcher2.group(3));// 把获取到的图片地址添加到列表中
    }
    hasPic = matcher.find();// 判断是否还有img标签
    }

    }
    return srcList;
    }

}
