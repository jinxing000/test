package com.ef.golf.exception;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 15:19
 */
public class MemberProveException extends Exception {

    private int retCode = 0;

    public MemberProveException(int retCode) {
        this.retCode = retCode;
    }


    public int getRetCode() {
        return retCode;
    }
}
