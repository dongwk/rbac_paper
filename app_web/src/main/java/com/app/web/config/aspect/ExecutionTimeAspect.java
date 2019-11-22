package com.app.web.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 *
 * 接口超时日志
 * 
 * @author dongwk
 * @date 2018/11/27
 */
@Component
@Aspect
@Slf4j
public class ExecutionTimeAspect {

    /**
     * 接口超时日志
     */
    private static final Logger INTERFACE_TIMEOUT_LOG = LoggerFactory.getLogger("EXECUTION_TIMEOUT_LOG");

    private final static long SERVICE_TIMEOUT = 2000;
    private final static long MAPPER_TIMEOUT = 1000;

    @Pointcut("execution(* com.app.service..*.*(..))")
    public void serviceLog(){}
    @Pointcut("execution(* com.app.mapper..*.*(..))")
    public void mapperLog(){}

    @Around(value = "serviceLog()", argNames = "pjp")
    public Object serviceAround(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs();

        Object obj = null;
        try {
            long start = System.currentTimeMillis();
            obj = pjp.proceed(args);
            long finish = System.currentTimeMillis();
            long useTime = finish - start;

            if (useTime >= SERVICE_TIMEOUT) {
                INTERFACE_TIMEOUT_LOG.warn("service timeout interface:[{}].useTime:[{}].args:{} timeout:[{}]",
                        pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName(),
                        useTime, Arrays.toString(args), SERVICE_TIMEOUT);
            }
            return obj;
        } catch (Throwable e) {//处理你的异常
            log.error("ExecutionTimeAspect service timeout {}", ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @Around(value = "mapperLog()", argNames = "pjp")
    public Object mapperAround(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs();

        Object obj = null;
        try {
            long start = System.currentTimeMillis();
            obj = pjp.proceed(args);
            long finish = System.currentTimeMillis();
            long useTime = finish - start;

            if (useTime >= MAPPER_TIMEOUT) {
                INTERFACE_TIMEOUT_LOG.warn("timeout interface:[{}].useTime:[{}].args:{} timeout:[{}]",
                        pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName(),
                        useTime, Arrays.toString(args), MAPPER_TIMEOUT);
            }
            return obj;
        } catch (Exception e) {
            log.error("ExecutionTimeAspect mapper timeout {}", ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }
}

