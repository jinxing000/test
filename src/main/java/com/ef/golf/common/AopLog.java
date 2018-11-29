package com.ef.golf.common;

import com.ef.golf.util.TimeFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:29
 */
public class AopLog {

    private transient final Log log = LogFactory.getLog(getClass());

    // 执行顺序 doBefore->doAround.proceed->doAfter

    public void doBefore(JoinPoint jp) throws Exception {
        log.info("请求时间:\t\t\t"+ TimeFormat.formatDatetime(new Date()));
        log.info("请求函数:\t\t\t"+jp.getSignature().getName());
        String arg ="";
        for (Object o : jp.getArgs())
            arg +=o.toString()+"\t\t\t";
        log.info("请求参数:\t\t\t"+arg);
    }

    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("返 回 值:\t\t\t" + pjp.proceed().toString());
    }
    public void doAfter(JoinPoint jp) {
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        String methodName = jp.getSignature().getName();
        log.error("异常方法:\t\t\t" + methodName);
        log.error("异常内容:\t\t\t" );
    }
}
