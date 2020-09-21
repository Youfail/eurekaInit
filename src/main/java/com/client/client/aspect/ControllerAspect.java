package com.client.client.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAspect {
    private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    @Pointcut("execution(public * com.client.client.controller..*.*(..))")
    public void webLog(){}

    @Around("webLog()")
    public  Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object o = proceedingJoinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(request.getHeader("x-forwarded-for"));
        logger.info(proceedingJoinPoint.getSignature().getName());
        logger.info(o.toString());
        return o;
    }
}
