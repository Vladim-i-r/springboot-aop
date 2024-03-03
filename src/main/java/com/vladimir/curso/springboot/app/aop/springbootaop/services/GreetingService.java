package com.vladimir.curso.springboot.app.aop.springbootaop.services;

import org.springframework.stereotype.Service;

@Service
public interface GreetingService {
    String sayHello(String person, String phrase);
    String sayHelloError(String person, String phrase);
    
} 
