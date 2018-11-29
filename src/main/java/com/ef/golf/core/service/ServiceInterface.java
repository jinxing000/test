package com.ef.golf.core.service;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public interface ServiceInterface {

    public static Integer EMPTY_RESULT_NUMBER = Integer.valueOf(0);
    public static String EMPTY_RESULT_STRING = StringUtils.EMPTY;
    public static Map EMPTY_RESULT = MapUtils.EMPTY_MAP;
    public static List EMPTY_RESULT_LIST = ListUtils.EMPTY_LIST;


    public Object doService() throws Exception;

}
