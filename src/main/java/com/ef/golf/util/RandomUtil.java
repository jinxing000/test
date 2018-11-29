package com.ef.golf.util;


import java.util.Random;

/**
 * create by xzw
 * 2017年10月13日16:33:09
 */
public class RandomUtil {


    /**
     *
     * @param len 指定要获取的随机数的长度
     * @return 返回指定长度的随机数
     */
    public static  String getRandom(int len){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(len);
        for(int i=0;i<len;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);

        }
        return sb.toString();

    }
}
