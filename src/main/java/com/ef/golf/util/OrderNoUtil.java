package com.ef.golf.util;


import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by xzw
 * 2018年1月26日10:55:32
 * 用于生成订单编号的工具类
 */
@Repository
public class OrderNoUtil {
    /**
     * @param type 01场地 02教练 03球童 04球队 05赛事 06商家 07商城 08旅游 09课程 10愿望
     * @param phoneNumber 用于生成订单编号的电话号码
     * @return 订单编号
     */
    public synchronized String orderNoGenerate(String type,String phoneNumber){
        Long date= new Date().getTime();
        String numTime=date.toString();
        String orderNo= type+
                numTime.substring(numTime.length()-10,numTime.length())+
                phoneNumber.substring(phoneNumber.length()-4,phoneNumber.length());
        return orderNo;
    }

    /**
     * 生成流水号
     * @param type 01充值 02提现 03退款 04交易 05 赠送 06 转账
     * @param phoneNumber 手机号
     * @return 对应类型的流水号
     */
    public synchronized String serialNoGenerate(String type,String phoneNumber){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String number=sdf.format(date);
        String end=null;
        switch (type){
            case "01":
                end="r"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
            case "02":
                end="w"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
            case "03":
                end="b"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
            case "04":
                end="t"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
            case "05":
                end="z"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
            case "06":
                end="j"+number+RandomUtil.getRandom(4)+phoneNumber;
                return end;
        }
        return end;
    }

    /**
     * 生成专属码
     * @return 专属码
     */
    public synchronized String exclusiveCodeGenerate(){
        Long date=System.currentTimeMillis()/1000;
        String a=date.toString();
        String left=a.substring(a.length()-5,5);
        String right=RandomUtil.getRandom(3);
        String end=left+right;
        return end;
    }
}
