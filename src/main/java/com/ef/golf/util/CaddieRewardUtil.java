package com.ef.golf.util;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@DisallowConcurrentExecution
public class CaddieRewardUtil {


    @Autowired
    private RedisBaseDao redisBaseDao;

    public boolean caddieReward(String userId,String recipient){
            boolean flag = true;
            int count = 0; //计数器
         // Map内打赏人id为key  打赏人的被打赏人存list  每天00:00定时创建redisMap 86400秒过时?
                try{
                    Map<String,Object>caddieRewardMap = redisBaseDao.getMap("caddieReward");

                    List<String>caddieRewardList = (List<String>) caddieRewardMap.get(userId);
                    /** 废弃 一天只能打赏四次 */
                    /*if(null!= caddieRewardList) {
                        for (String s : caddieRewardList) {
                            if (s.equals(recipient)) {
                                count++;
                            }
                        }
                    }*/
                    /*if (count == 4) {
                        flag = false;
                    }*/

                    if(4<=caddieRewardList.size()){
                        flag = false;
                    }
                }catch (Exception e){
                    System.out.println("==================判断球童打赏四次异常");
                }

            return flag;
    }

    public void setCaddieRewardMap(String userId,String recipient){
        try{
            Map<String,Object>caddieRewardMap = redisBaseDao.getMap("caddieReward");//应该不可能为null
            if(redisBaseDao.exist("caddieReward")) {

                List<String> caddieRewardList = (List<String>) caddieRewardMap.get(userId); //取出来赋值 如果为null new ArrayList<>();

                if (null == caddieRewardList) {
                    caddieRewardList = new ArrayList<>();
                    caddieRewardList.add(recipient);
                } else {
                    caddieRewardList.add(recipient);
                }
                caddieRewardMap.put(userId, caddieRewardList);//最后重新扔进map  需要重新设置过期时间
                redisBaseDao.setMap("caddieReward",caddieRewardMap,(int)this.getTomorrowZeroSeconds());
            }
        }catch (Exception e){
            System.out.println("=======================设置球童打赏数据异常");
        }

    }

    @Scheduled(cron = "0 1 0 * * ?")//每天凌晨0点0一分执行
    //@Scheduled(cron = "0 35 14 * * ?")
    //@Scheduled(cron = "*/5 * * * * ?")
    public void createCaddieRewardMap(){
        try{
            Map<String,Object> caddieRewardMap = new HashMap<>();
            redisBaseDao.setMap("caddieReward",caddieRewardMap,86400);
        }catch (Exception e){
            System.out.println("=================Redis创建球童打赏map异常");
        }
    }

    public long getTomorrowZeroSeconds(){
        long tomorrowzeroSeconds = 0;
        try{
            long current = System.currentTimeMillis();// 当前时间毫秒数
            System.out.println("当前"+current);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            long tomorrowzero = calendar.getTimeInMillis();
            tomorrowzeroSeconds = (tomorrowzero- current) / 1000;
        }catch (Exception e){
            System.out.println("==============获取结束秒数异常");
        }

        return tomorrowzeroSeconds;
    }
}
