package com.redhat.example.fsw.poc.soap_helloworld.bean;

import org.switchyard.component.bean.Service;

import com.redhat.fsw.poc.soap_helloworld.service.HelloWorldService;

@Service(HelloWorldService.class)
public class HelloWorldServiceBean1 implements HelloWorldService {

    @Override
    public String sayHelloWorld() {
        // TODO Auto-generated method stub
        return null;
    }

}
