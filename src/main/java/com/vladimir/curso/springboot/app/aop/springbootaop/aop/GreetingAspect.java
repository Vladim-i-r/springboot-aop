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

    @After("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")   //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    public void loggerAfter(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Despues: " + method + " con los argumentos:" + args);
    } 

    @AfterReturning("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")   //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    public void loggerAfterRet(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Despues de retornar: " + method + " con los argumentos:" + args);
    }
     
    @AfterThrowing("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")   //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    public void loggerAfterThr(JoinPoint joinPoint){          // une la ejecucion de un aspecto a la llamada de un metodo
    
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()); //Esto regresa un objeto pero con el arrays lo transformamos
        logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos:" + args);
    } 

    @Around("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method =  joinPoint.getSignature().getName();
        String args =Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            logger.info("El metodo " + method + "() con los parametros: " + args);  //BEFORE
            result = joinPoint.proceed();
            logger.info("El metodo " + method + "() retorna el resultado: " + result); //AFTER
            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del metodo " + method + "()");
            throw e;
        }
        //return result;
    }


}
