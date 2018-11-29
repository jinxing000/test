package com.ef.golf.core.structure.base;

import java.util.ResourceBundle;

public class BaseBizImpl {
	protected static final String INTERFACE_URL =  ResourceBundle.getBundle("conf/sys").getString("INTERFACE_URL");
	protected static final String DEFAULTPASSWORD =  ResourceBundle.getBundle("conf/sys").getString("defaultpassword");

	
}
