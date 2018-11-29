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

import com.ef.golf.pojo.Tickets;
import com.ef.golf.vo.CreditVo;
import com.ef.golf.vo.ShopSendTicketVo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商城购买商品时赠券工具类
 */
public class SystemSendTicketUtils {


    public static Map<String,Object> shopSendTicket(List<Tickets>list, List<ShopSendTicketVo>list1) {

        //品牌规则 购物满1000送一张200满减券 满500送50满减券 满100送20满减券  现有一单2688元 实现分配三种券
        /*int yi = (int)(jiage/1000);
        int er = (int)(jiage%1000/500);
        int san = (int)(jiage%1000%500/100);*/


        Map<String,Object>map = new HashMap<>();

        List<ShopSendTicketVo>list2 = new ArrayList<>();//优惠券明细
        List<CreditVo> creditVoList=new ArrayList<>();
        int j=0;
        for (ShopSendTicketVo sstv:list1) {

            CreditVo creditVo=new CreditVo();
            creditVo.setIndex(j);
            creditVo.setGoodsId(sstv.getGoodsId());
            creditVo.setCredit(0.0);//初始化为0
            creditVoList.add(creditVo);
            if(sstv.getReduction()==1){
                /**
                 * 专门为返回单件商品的券总额  start  20180905  尹金星
                 */


                double credit=0;
                //======专门为返回单件商品的券总额  end  20180905  尹金星=====

                int yushu =(int)(sstv.getPrice()*(sstv.getCredit()/100.0));
                int tickets = 0;
                int count=0;
                for (int i=0;i<list.size();i++){
                    tickets =(int) (yushu/list.get(i).getSpecialMoney());
                    //当前金额除优惠券money 余数
                    yushu = (int) (yushu%list.get(i).getSpecialMoney());
                       ShopSendTicketVo shopSendTicketVo2 = new ShopSendTicketVo();
                        shopSendTicketVo2.setGoodsId(sstv.getGoodsId());
                        shopSendTicketVo2.setTicketId(list.get(i).getTicketId()+"");
                        shopSendTicketVo2.setPrice(list.get(i).getSpecialMoney());
                        count = tickets*sstv.getCount();
                        shopSendTicketVo2.setCount(count);

                       list2.add(shopSendTicketVo2);

                       credit=credit+list.get(i).getSpecialMoney()*tickets;//对应商品的单件商品应增券总额  注意是单件的乘的是tickets
                }
                creditVo.setCredit(credit);
                creditVoList.set(j,creditVo);
                j++;
            }
        }
        map.put("sstv",list2);//优惠券明细
        map.put("creditVoList",creditVoList);
        return map;
    }

    public static void main(String[] args) {
        //获取当前时间三个月后的时间
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,2);

        System.out.println(simpleDateFormat.format(calendar.getTime()));

       /* Map<String,Object>map = shopSendTicket(,1,1);
        map.forEach((key,value) ->{
            List<ShopSendTicketVo>list = (List<ShopSendTicketVo>) map.get(key);
            for (ShopSendTicketVo s:list
                    ) {
                System.out.println("商品id--->"+s.getGoodsId()+"券面值--->"+s.getPrice()+"券模板id--->"+s.getTicketId()+"数量--->"+s.getCount());
            }
        });*/
    }
}
