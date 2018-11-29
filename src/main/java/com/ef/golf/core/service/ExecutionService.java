package com.ef.golf.core.service;

import com.ef.golf.common.Constant;
import com.ef.golf.common.RetHandle;
import com.ef.golf.core.exception.ExceptionTranslator;
import com.ef.golf.core.exception.ValidatorException;
import com.ef.golf.exception.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;

@Service
public class ExecutionService {
    private final Log logger = LogFactory.getLog(getClass());

    @Resource
    private ServiceProxy serviceProxy;
    @Resource
    private ExceptionTranslator exceptionTranslator;
    @Resource
    private AppClientConvert appClientConvert;

    public ExecutionResult executeService(MessageHeaders headers, ServiceInterface service) {
        appClientConvert.setMessageHeaders(headers);
        ExecutionResult result = getResult(service);
        appClientConvert.cleanThreadCache();
        return result;
    }

    public ExecutionResult getExceptionResult(Exception e) {
        String responseCode = exceptionTranslator.getCode(e);
        String exception = e.getClass().getSimpleName();
        String exceptionText = exceptionTranslator.getMessage(e);
//        return ExecutionResult.getException(responseCode, exception, exceptionText);
        return ExecutionResult.getException(responseCode, Constant.ERR_UNKNOW, exceptionText);
    }

    public ExecutionResult getExceptionResult(int retCode) {
        return ExecutionResult.getException(ExecutionResult.CODE_SUCCESS,retCode, RetHandle.getMessage(retCode));
    }


    public ExecutionResult getExceptionResult(Exception e,int retCode) {
        String exceptionText = exceptionTranslator.getMessage(e);
        return ExecutionResult.getException(ExecutionResult.CODE_SUCCESS,retCode, exceptionText);
    }



    private ExecutionResult getResult(ServiceInterface service) {
        try {
            Object result = serviceProxy.doService(service);
            return ExecutionResult.getSuccess(result);
        }catch (SystemException e){
            return getExceptionResult(e.getRetCode());
        }catch (LoginException e){
            return getExceptionResult(e.getRetCode());
        }catch (QueryException e){
            return getExceptionResult(e.getRetCode());
        }catch (DemandException e){
            return getExceptionResult(e.getRetCode());
        }catch (MallException e){
            return getExceptionResult(e.getRetCode());
        }catch (ValidatorException e){
            return getExceptionResult(e,Constant.ERR_VALIDATOR);
        }catch (Exception e) {
            logger.error("{}", e);
            System.out.println(e);
            e.printStackTrace();
            return getExceptionResult(Constant.ERR_UNKNOW);
        }
    }
}
