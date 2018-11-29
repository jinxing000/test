package com.ef.golf.core.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceProxy {

    @Resource
    private ValidatorComponent validatorComponent;

    public Object doService(ServiceInterface service) throws Exception {
        validatorComponent.validate(service);
        return service.doService();
    }
}
