package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class RecordTimeAspect {

    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjd) throws Throwable {

        //1.记录方法运行的开始时间
        long begin = System.currentTimeMillis();

        //2.执行原始的方法
        Object result = pjd.proceed();

        //3.记录方法运行的结束时间，记录耗时
        long end = System.currentTimeMillis();
        log.info("方法{}执行耗时: {}ms",pjd.getSignature(), (end - begin));
        return result;
    }


}
