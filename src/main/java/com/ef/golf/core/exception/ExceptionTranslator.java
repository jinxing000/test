package com.ef.golf.core.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Component
public class ExceptionTranslator {

	private Properties defaultCodes = new DefaultExceptionCode();

	@Resource
	private Properties exceptionMessages;

	@Resource
	private Properties exceptionCodes;

	public String getMessage(Exception e) {
        String message = getProperties(e.getClass(), exceptionMessages);
        if (StringUtils.isEmpty(message)) {
			StringBuilder sb = new StringBuilder();
            buildMessage(e, sb);
			message = sb.toString();
        }
        return message;
	}

	private void buildMessage(Throwable e, StringBuilder sb) {
		String message = e.getMessage();
		if (StringUtils.isNotEmpty(message) && sb.indexOf(message) < 0) {
			sb.append(message);
		}
		Throwable cause = e.getCause();
		if (cause == null) return;
		buildMessage(cause, sb);
	}

	public String getCode(Exception e) {
		String result = getProperties(e.getClass(), exceptionCodes);
		if (StringUtils.isNotEmpty(result)) {
			return result;
		}
		return getProperties(e.getClass(), defaultCodes);
	}

	private String getProperties(Class<?> clazz, Properties properties)
	{
		if (properties == null) {
			return null;
		}
		if (clazz.equals(Object.class)){
			return null;
		}
		String result = properties.getProperty(clazz.getSimpleName());
		if (StringUtils.isNotEmpty(result)) {
			return result;
		}
		return getProperties(clazz.getSuperclass(), properties);
	}

}
