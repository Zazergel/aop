package com.zazergel.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.zazergel.aop.user.service.UserService) " +
              "|| within(com.zazergel.aop.order.service.OrderService)")
    public void applicationServicePointcut() {
    }

    @Before("applicationServicePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Call method: {}.{} with args: {}", className, methodName, joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "applicationServicePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.error("Exception in method: {}.{} with message: {}", className, methodName,
                e.getMessage());
    }
}

