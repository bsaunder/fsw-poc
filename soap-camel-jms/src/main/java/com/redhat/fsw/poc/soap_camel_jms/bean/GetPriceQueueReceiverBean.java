/**
 * 
 */
package com.redhat.fsw.poc.soap_camel_jms.bean;

import org.switchyard.component.bean.Service;

/**
 * Receives the Message on the GetPriceQueue.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@Service(GetPriceQueueReceiver.class)
public class GetPriceQueueReceiverBean implements GetPriceQueueReceiver {

    /*
     * (non-Javadoc)
     * @see com.redhat.fsw.poc.soap_camel_jms.bean.GetPriceQueueReceiver#receive()
     */
    @Override
    public void receive() {
        System.out.println("Just Received Message on GetPriceQueue");
    }

}
