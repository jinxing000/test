package com.ef.golf.core.service;

/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:38
 */
public class AbstractService implements ServiceInterface{
    public Object getResult(){
        return EMPTY_RESULT;
    }

    public void execute() {
    }

    public Object doService() throws Exception{
        execute();
        return getResult();
    }
}
