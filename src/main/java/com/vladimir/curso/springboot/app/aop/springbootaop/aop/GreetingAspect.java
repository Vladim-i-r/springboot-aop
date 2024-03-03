package com.vladimir.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {
    private Logger logger =  LoggerFactory.getLogger(this.getClass()); //? Registra eventos
                    ////d |* con el asterisco regresa cualquier cosa                             *             *     
    @Before("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")   //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    public void loggerBefore(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Antes: " + method + " con los argumentos:" + args);
    } 
    
}
