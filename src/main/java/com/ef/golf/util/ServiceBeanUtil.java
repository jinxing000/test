package com.ef.golf.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 08:57
 */
public class ServiceBeanUtil {

    private static Log log = LogFactory.getLog(ServiceBeanUtil.class);
    private static final long serialVersionUID = 1L;

    /**
     * spring context file path
     */
    private final static String beanConfig = "META-INF/golf-config.xml";

    private static BeanFactory factory;

    private static Properties props = getProps();

    /**
     * 初始化context对象，通过beanConfig指定的context file
     */
    public static void init() {
        factory = new ClassPathXmlApplicationContext(beanConfig);
        log.debug("初始化context:" + beanConfig);
    }

    /**
     * 通过beanId得到factory中的bean实例
     *
     * @param beanId
     * @return Object
     */
    public static Object getBean(String beanId) {
        Object obj = null;

        if (factory == null) {
            synchronized (ServiceBeanUtil.class) {
                if (factory == null) {
                    init();
                }
            }
        }
        if (factory != null)
            obj = factory.getBean(beanId);
        return obj;
    }

    /**
     * 获得BeanFactory实例
     *
     * @return the BeanFactory
     */
    public static BeanFactory getBeanFactory() {
        if (factory == null) {
            synchronized (ServiceBeanUtil.class) {
                if (factory == null) {
                    init();
                }
            }
        }
        return factory;
    }

    /**
     * 获得系统配置属性对象
     *
     * @return 属性对象
     */
    public static Properties getProps() {
        Properties conf = null;
        try {
            conf = (Properties) getBean("config");
        } catch (NoSuchBeanDefinitionException e) {
            log.error("寻找config的名字！失败");
        }
        return conf;
    }

    /**
     * 得到系统配置属性
     *
     * @param name
     * @return 属性字符值
     */
    public static String getConfig(String name) {
        if (props != null)
            return (String) props.getProperty(name);
        else
            return null;
    }
}
