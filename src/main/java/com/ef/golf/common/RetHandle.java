package com.ef.golf.common;

import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/1/13.
 * Date: 2017/1/13 20:51
 */
public class RetHandle {
    private static Map retCodeMap = Consts.RET_CODE;

    public static String getMessage(Object ret) {
        String codeStr = String.valueOf(ret);
        return retCodeMap.get(codeStr) == null ? "未知返回码 ！"
                : retCodeMap.get(codeStr).toString();
    }
}
