package com.vladimir.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)  // Intercepta en orden pero tambien segun el tipo del aspect.
@Component
@Aspect
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    

    @Before("GreetingServicePointcuts.greetingFooAspectPointCut()")   //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    public void loggerBefore(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Order 1, Before: " + method + " invocado con los parametros:" + args);
    } 

    @After("GreetingServicePointcuts.greetingFooAspectPointCut()")   //
    public void loggerAfter(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Order 1, After: " + method + " con los argumentos:" + args);
    } 
}
