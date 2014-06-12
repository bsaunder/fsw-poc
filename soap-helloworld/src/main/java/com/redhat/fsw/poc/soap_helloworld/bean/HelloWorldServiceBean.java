/**
 * 
 */
package com.redhat.fsw.poc.soap_helloworld.bean;

import org.switchyard.component.bean.Service;

import com.redhat.fsw.poc.soap_helloworld.service.HelloWorldService;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@Service(HelloWorldService.class)
public class HelloWorldServiceBean implements HelloWorldService {

    /* (non-Javadoc)
     * @see com.redhat.example.fsw_poc_soap_helloworld.HelloWorldService#sayHelloWorld()
     */
    @Override
    public String sayHelloWorld() {
        return "Hello World";
    }

}
