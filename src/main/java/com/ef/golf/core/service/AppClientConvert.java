package com.ef.golf.core.service;

import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class AppClientConvert {

    private ThreadLocal<MessageHeaders> messageHeader = new ThreadLocal<MessageHeaders>();

    public static final String HEADER_NAME_USERAGENT        = "user-agent";
    public static final String HEADER_NAME_TOKEN            = "x-trd-token";
    public static final String HEADER_NAME_DEVICEID         = "x-trd-deviceid";
    public static final String HEADER_NAME_DEVICETYPE       = "x-trd-devicetype";
    public static final String HEADER_NAME_DEVICESCREEN     = "x-trd-devicescreen";
    public static final String HEADER_NAME_OSTYPE           = "x-trd-ostype";
    public static final String HEADER_NAME_OSVER            = "x-trd-osver";
    public static final String UID                          = "uid";
    public static final String SID                          = "sid";

    public void cleanThreadCache(){
        messageHeader.remove();
    }

    public void setMessageHeaders(MessageHeaders headers) {
        messageHeader.set(headers);
    }

    public String getToken(){
        MessageHeaders headers = messageHeader.get();
        return headers.get(HEADER_NAME_TOKEN, String.class);
    }

    public String getUid(){
        return messageHeader.get().get(UID,String.class);
    }

    public String getSid(){
        return messageHeader.get().get(SID,String.class);
    }
}
