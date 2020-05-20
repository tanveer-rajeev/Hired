package com.pppfreak.Hired.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Aspect
@Service
public class ExceptionHandlingByAspect {

    private Logger logger = Logger.getLogger(getClass().getName());


    @Pointcut("execution(* com.pppfreak.Hired.serviceimpl.*.jobApply(..))")
    private void forJobApplyForm(){}

    @Pointcut("execution(* com.pppfreak.Hired.serviceimpl.*.createJob(..))")
    private void forCreateJob(){}

    @Pointcut("execution(* com.pppfreak.Hired.serviceimpl.*.getCompanyProfileById(..))")
    private void forGetCompanyProfileId(){}

    @Around("forGetCompanyProfileId()")
    private Object jobApplyFormExceptionHandling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String method = proceedingJoinPoint
                .getSignature()
                .toShortString();
        logger.info("Got an exception in this " + method + " method");
        Object returnValue;
        try {
            returnValue = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            logger.warning("user not found by this id " + ex.getMessage());
            throw ex;
        }

        return returnValue;

    }
}
