/**
 * 
 */
package com.redhat.fsw.poc.soap_camel_jms.bean;

/**
 * Defines a GetPriceQueue Message Receiver.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public interface GetPriceQueueReceiver {

    /**
     * Handles the Received Message.
     */
    public void receive();
}
