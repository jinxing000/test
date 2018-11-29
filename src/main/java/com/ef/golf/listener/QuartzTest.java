package com.ef.golf.listener;

import com.ef.golf.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.ef.golf.listener
 * Administrator
 * 2018/5/31 14:18
 *
 *
 *
 */
@Service
/*@PropertySource(value = "classpath*:quartz.properties",ignoreResourceNotFound = true)*/
public class QuartzTest implements Runnable{

    /*private QuartzTest(){}
    private static final QuartzTest qt = new QuartzTest();

    public static synchronized QuartzTest getInstance(){
        return qt;
    }*/

    @Override
    public void run() {

    }


    public static void quartzTest2(){
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {

           }
       });
    }


}
