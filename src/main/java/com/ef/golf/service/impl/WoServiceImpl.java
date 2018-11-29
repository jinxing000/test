package com.ef.golf.service.impl;

import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.WoService;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
@Component
public class WoServiceImpl implements BeanNameAware,ResourceLoaderAware{
    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }
    /*@Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private Destination destination;
    @Override
    public IfunResult sendMessage(final String ss) {
        System.out.println("准备发送消息");
        jmsTemplate.send(destination,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(ss);
            }
        });
        System.out.println("消息已发送");
        return IfunResult.ok("");
    }*/
}
