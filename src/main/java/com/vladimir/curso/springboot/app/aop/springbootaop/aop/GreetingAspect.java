package com.vladimir.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {
    private Logger logger =  LoggerFactory.getLogger(this.getClass()); //? Registra eventos

                    ////d |* con el asterisco regresa cualquier cosa                             *             *     
    @Before("GreetingServicePointcuts.greetingLoggerPointCut()")   
    public void loggerBefore(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Order 2, Before: " + method + " con los argumentos:" + args);
    } 

    @After("GreetingServicePointcuts.greetingLoggerPointCut()")   //
    public void loggerAfter(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Order 2, After: " + method + " con los argumentos:" + args);
    } 

    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut()")   //
    public void loggerAfterRet(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("After Returning: " + method + " con los argumentos:" + args);
    }
     
    @AfterThrowing("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")   //! antes sayHello(..)) en lugar de *(..)) causaba que no entrara despues, por lo que se dejo con el *, mas general
    public void loggerAfterThr(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("After throwing exception: " + method + " con los argumentos:" + args);
    } 

    @Around("GreetingServicePointcuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method =  joinPoint.getSignature().getName();
        String args =Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            logger.info("Around-before, El metodo " + method + "() con los parametros: " + args);  //BEFORE
            result = joinPoint.proceed();
            logger.info("Around-after, El metodo " + method + "() retorna el resultado: " + result); //AFTER
            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del metodo " + method + "()");
            throw e;
        }
        //return result;
    }


}
