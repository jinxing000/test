/*
 * goeasy.java       1.0    2017年10月16日
 *
 * Copyright (c) 2011, 2014 Tianjin YiDianFu Network Technology Co. Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * YiDianFu Network Technology Co. Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with YiDianFu.
 */
package com.ef.golf.util;

import io.goeasy.GoEasy;

/**
 *
 *
 * @author Administrator 2017年10月16日 下午5:30:29
 * @version 1.0
 */
public class GoEasyUtil {
    static String RESTHOST = Constants.RESTHost;
    static String COMMONKEY = Constants.Commonkey;

    public static void pushMessage(String userId, String message) {
        GoEasy goEasy = new GoEasy(RESTHOST, COMMONKEY);
        goEasy.publish(userId, message);
    }
}