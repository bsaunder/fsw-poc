/**
 * 
 */
package com.redhat.fsw.poc.soap_helloworld.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.redhat.fsw.poc.soap_helloworld.service.HelloWorldService;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@Service(HelloWorldService.class)
public class HelloWorldServiceBean implements HelloWorldService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /* (non-Javadoc)
     * @see com.redhat.example.fsw_poc_soap_helloworld.HelloWorldService#sayHelloWorld()
     */
    @Override
    public String sayHelloWorld() {
        this.logger.info("Saying 'Hello World'");
        return "Hello World";
    }

}
