package com.ef.golf.core.structure.base;

import java.util.ResourceBundle;


/**
 * 业务层基类
 * 
 * @author ye.jinhui
 * @date 2016年3月23日
 */
public class BaseBiz {
	protected static final String INTERFACE_URL =  ResourceBundle.getBundle("conf/sys").getString("INTERFACE_URL");
	protected static final String DEFAULTPASSWORD =  ResourceBundle.getBundle("conf/sys").getString("defaultpassword");
	protected static final String MALL_MESSAGE =  ResourceBundle.getBundle("conf/redis").getString("MALL_MESSAGE");
	
}