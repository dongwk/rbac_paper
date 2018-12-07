package com.app.manage.web.aspect;

import com.app.manage.web.annotation.InterfaceProperty;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tianshu on 16/1/28 下午10:35.
 */
@Component
@Aspect
@Slf4j
public class SystemRequestAspect {

    /**
     * 接口超时日志
     */
    private static final Logger INTERFACE_TIMEOUT_LOG = LoggerFactory.getLogger("INTERFACE_TIMEOUT_LOG");

    @Pointcut("execution(* com.app.service.*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @Around(value = "webLog()", argNames = "pjp")
    public Object validator(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        try {
            long start = System.currentTimeMillis();
            Object obj = pjp.proceed(args);
            long finish = System.currentTimeMillis();
            long useTime = finish - start;
            if (useTime > 100) {
                INTERFACE_TIMEOUT_LOG.info("接口超时,interface:[{}].useTime:[{}].settingUseTime:[{}]",
                        new Object[]{pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName(),
                                useTime});
            }

            return obj;
        } catch (Throwable e) {//处理你的异常
            throw e;
        } finally {//处理其他
        }
    }
}

