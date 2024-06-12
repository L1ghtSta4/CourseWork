package org.example.logger;



import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @AfterReturning(pointcut = "@annotation(org.example.logger.Loggable)", returning = "result")
    public void logControllerMethods(Object result) {
        logger.info("Controller method executed successfully with result: {}", result);
    }
}

