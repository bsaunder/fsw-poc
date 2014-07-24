/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.common.service;

/**
 * Common Interface for Sending Messages to Queues.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public interface QueueSender {

    /**
     * Sends the Message to the Queue.
     */
    public void send();
}
