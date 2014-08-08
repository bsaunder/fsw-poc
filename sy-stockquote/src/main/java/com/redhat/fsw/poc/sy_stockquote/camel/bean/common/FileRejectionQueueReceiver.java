/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.common;

import com.redhat.fsw.poc.sy_stockquote.common.bean.TransferBean;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public interface FileRejectionQueueReceiver {
    public void receive(TransferBean transferBean);
}
