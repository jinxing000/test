package com.ef.golf.test;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * com.ef.golf.test
 * Administrator
 * 2018/7/11 13:53
 */
public class Singleton {/** 单例懒汉式线程安全+效率  DCL双检查锁机制*/

   private static Singleton singleton;//懒汉式 线程不安全加了sync同步
    //private static Singleton singleton = new Singleton();//饿汉式
   private String  user;

    public Singleton() {
    }

    /*public static Singleton getInstance() {
        return singleton;
    }

    public static void setSingleton(Singleton singleton) {
        Singleton.singleton = singleton;
    }*/
    public static Singleton getInstance() {
       try {
           if(null==singleton){
               synchronized (Singleton.class){
                   if(null==singleton){
                       singleton = new Singleton();
                   }
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return singleton;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static void main(String[] args) {
        Integer totalScore = null;

        System.out.println(totalScore<0?0:totalScore);

       /* Singleton singleton = Singleton.getInstance();
        singleton.setUser("1");
        logger.setLevel(Level.SEVERE);
        logger.info("sss");
        System.out.println(singleton.getUser());*/
    }
}
