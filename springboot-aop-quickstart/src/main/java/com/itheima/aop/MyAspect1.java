package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt() {
    }
    // 前置通知 - 在目标方法运行之前运行
    @Before("pt()")
    public void before()
    {
        log.info("MyAspect1 before");
    }

    // 环绕通知 - 在目标方法运行之前和之后运行
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjd) throws Throwable {
        log.info("MyAspect1 around before");
        Object result = pjd.proceed();
        log.info("MyAspect1 around after");
        return result;
    }

    // 后置通知 - 在目标方法运行之后运行，无论目标方法是否发生异常
    @After("pt()")
    public void after()
    {
        log.info("MyAspect1 after");
    }

    //返回后通知 - 目标方法运行之后运行，如果出现异常不会运行
    @AfterReturning("pt()")
    public void afterReturning()
    {
        log.info("MyAspect1 afterReturning");
    }

    //异常后通知 - 目标方法运行之后运行，只有出现异常才会运行
    @AfterThrowing("pt()")
    public void afterThrowing()
    {
        log.info("MyAspect1 afterThrowing");
    }
}
