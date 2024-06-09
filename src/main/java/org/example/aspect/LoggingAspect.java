package org.example.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* org.example.coursework.service.*.*(..))")
    private void serviceMethods() {
    }

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before executing: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After returning from: " + joinPoint.getSignature().toShortString());
    }
}
