package org.zerock.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAdvice {

    @Before("execution(* org.zerock.controller.service.AopTestService*.*(..))")
    public void logBefore(){
        log.info("=============================");
    }

    @Around("execution(* org.zerock.controller.service.AopTestService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {

        long start=System.currentTimeMillis();

        log.info("Target :" + pjp.getTarget());
        log.info("Param :" + Arrays.toString(pjp.getArgs()));

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end= System.currentTimeMillis();

        log.info("Time :" + (end - start));

        return result;
    }

}
