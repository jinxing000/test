package com.ef.golf.core.exception;

import java.security.AccessControlException;
import java.util.Properties;

public class DefaultExceptionCode extends Properties {

    public DefaultExceptionCode() {
        putServerError(Exception.class);
        putServerError(RuntimeException.class);
        putArgumentError(IllegalArgumentException.class);
        putNotLoginError(AccessControlException.class);

    }



//
//    /**
//     * Demo 异常测试
//     * 异常代码 505
//     * @param type
//     */
    private void putArgumentError(Class<?> type) {
        put(type.getSimpleName(), "400");
    }
    private void putNotLoginError(Class<?> type) {
        put(type.getSimpleName(), "401");
    }
    private void putServerError(Class<?> type) {
        put(type.getSimpleName(), "501");
    }


}
