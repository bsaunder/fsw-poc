package com.redhat.fsw.poc.sy_stockquote.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.redhat.fsw.poc.sy_stockquote.camel.bean.common.FileRejectionQueueReceiver;

/**
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Service(FileRejectionQueueReceiver.class)
public class FileRejectionReceiverBean implements FileRejectionQueueReceiver {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * (non-Javadoc)
     * 
     * @see com.redhat.example.fsw_poc_soap_helloworld.HelloWorldService#sayHelloWorld()
     */
    @Override
    public void receive() {
        this.logger.info("Just Received a FileRejection Queue Message");
    }

}
