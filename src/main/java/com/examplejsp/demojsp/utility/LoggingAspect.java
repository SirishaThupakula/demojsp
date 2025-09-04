package com.examplejsp.demojsp.utility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.examplejsp.demojsp.service.*.*(..)) || execution(* com.examplejsp.demojsp.controller.*.*(..))")
    public Object LogAroundAllComponents(ProceedingJoinPoint pip) throws Throwable{
        logger.info("Before method call "+pip.getSignature().toShortString());
       Object result = pip.proceed();
       logger.info("Method execution done "+pip.getSignature().toShortString());
        return result;
    }
}
