package com.ef.golf.listener;

import com.ef.golf.service.OrderService;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.ef.golf.listener
 * Administrator
 * 2018/5/31 16:47
 * 定时任务
 */
@Component
//@DisallowConcurrentExecution//当前的job执行后，再执行下次的job
public class QuartzCaddieOrderStatus {

    @Resource
    private OrderService orderService;

    // 球童订单成功预约到达指定时间自动修改状态为待评价 -->
    //@Scheduled(cron = "0 */1 * * * ?")//
    /*public void updateCaddieOrderState(){
        int i = orderService.updateCaddieOrderState();
        System.out.println("球童成功预约订单到达时间更新状态=="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"==更改"+i+"条");


        天津-西安 522 西安-兰州 174.5 兰州-武威 46.5 武威-兰州46.5 兰州-北京-天津 744.5
            1           2               3             4               4

    }*/
}
