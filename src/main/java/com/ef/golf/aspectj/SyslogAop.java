package com.ef.golf.aspectj;

import com.alibaba.fastjson.JSON;
import com.ef.golf.mapper.LogsMapper;
import com.ef.golf.pojo.Logs;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * com.ef.golf.aspectj
 * Administrator
 * 2018/10/13 10:57
   @Before – 目标方法执行前执行  前置通知 JoinPoint joinPoint
   @After – 目标方法执行后  执行      后置通知
   @AfterReturning – 目标方法返回后执行，如果发生异常不执行
   @AfterThrowing – 异常时执行      异常通知
   @Around – 在执行上面其他操作的同时也执行这个方法  环绕通知 ProceedingJoinPoint pjp   执行方法：pjp.proceed(); 要返回pjp
   Object[] getArgs：返回目标方法的参数
   Signature getSignature：返回目标方法的签名
   Object getTarget：返回被织入增强处理的目标对象
   Object getThis：返回AOP框架为目标对象生成的代理对象
 */


/** 设置该类在spring容器中的加载顺序 */
@Order(2)
@Aspect
@Component
public class SyslogAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    /** 获取方法的参数名 */
    @Autowired
    private LogsMapper logsMapper;

    @Pointcut("@annotation(com.ef.golf.aspectj.Syslog)")
    //@Pointcut("execution (* com.ef.golf.service.impl.*.*(..))")//||execution (* com.ef.golf.controller..*.*(..))
    public void logAspect(){}

    @AfterThrowing(pointcut = "logAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if(null!=ra)  {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            //请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            String jsonParameter = JSON.toJSONString(parameterMap);

            Map<String, Object> map = this.getMethodDescription(point);
            Logs logs = new Logs();
            logs.setCreateTime(new Date());
            logs.setRequestUrl(request.getRequestURI());
            logs.setRequestParameter(jsonParameter);
            logs.setServerAddr(request.getLocalAddr());
            logs.setRemoteAddr(GetRemoteIpUtil.getRemoteIp(request));
            logs.setSyslogTitle(map.get("title").toString());
            logs.setSyslogMethods(map.get("methods").toString());
            logs.setSyslogDescription(map.get("description").toString());
            logs.setArgs(map.get("args").toString());
            logs.setException("执行方法异常:" + map.get("methods").toString() + "执行方法异常:" + e);
				logsMapper.insertSelective(logs);
        }
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint point) {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        //请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        String jsonParameter = JSON.toJSONString(parameterMap);

        Object result = null;
        try {
            result = point.proceed();

            BufferedReader br = request.getReader();
            String str = "";
            StringBuffer sb = new StringBuffer();
            while((str = br.readLine()) != null){
                sb .append(str);
            }
            Map<String, Object> map = this.getMethodDescription(point);
               Logs logs = new Logs();
                    logs.setCreateTime(new Date());
                    logs.setRequestUrl(request.getRequestURI());
                    logs.setRequestParameter(jsonParameter);
                    logs.setServerAddr(request.getLocalAddr());
                    logs.setRemoteAddr(GetRemoteIpUtil.getRemoteIp(request));
                    logs.setSyslogTitle(map.get("title").toString());
                    logs.setSyslogMethods(map.get("methods").toString());
                    logs.setSyslogDescription(map.get("description").toString());
                    logs.setArgs(map.get("args").toString());
                    logs.setBody(sb.toString());
            logsMapper.insertSelective(logs);
        } catch (Throwable e) {
            logger.error("异常信息:{}", e.getMessage());
        }
        return result;
    }

    public Map<String, Object> getMethodDescription(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("title", method.getAnnotation(com.ef.golf.aspectj.Syslog.class).title());
                    map.put("methods", method.getAnnotation(com.ef.golf.aspectj.Syslog.class).methods());
                    map.put("args", this.getArgs(method, arguments));
                    String desc = method.getAnnotation(com.ef.golf.aspectj.Syslog.class).description();
                    if (StringUtils.isEmpty(desc))
                        desc = "执行成功!";
                    map.put("description", desc);
                    break;
                }
            }
        }
        return map;
    }

    private String getArgs(Method method, Object[] arguments) {
        StringBuilder builder = new StringBuilder("{");
        String params[] = parameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < params.length; i++) {
                builder.append(params[i]).append(" : ").append(arguments[i]).append(";");
        }
        return builder.append("}").toString();
    }

}

