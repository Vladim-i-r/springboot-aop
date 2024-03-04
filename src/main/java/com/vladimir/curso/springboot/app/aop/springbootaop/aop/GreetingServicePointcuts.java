package com.vladimir.curso.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GreetingServicePointcuts {
                                                                                            //Si solo se pone la interfaz, engloba mas |  el (..) significa con los argumentos que sean
    @Pointcut("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //? Esto es un punto de corte general y se reutiliza en los demas aspectos para no repetir
    public void greetingLoggerPointCut(){}

    @Pointcut("execution(String com.vladimir.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    public void greetingFooAspectPointCut(){}
}
