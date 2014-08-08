/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Headers;
import org.apache.camel.Properties;

/**
 * Utilities for working with Camel Exchanges.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class CamelExchangeUtil {
    
    private static List<String> keyList = new LinkedList<String>();
    {
        CamelExchangeUtil.keyList.add(CamelExchangeConstants.IS_MANIFEST_VALID);
        CamelExchangeUtil.keyList.add(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID);
        CamelExchangeUtil.keyList.add(CamelExchangeConstants.MANIFEST_OBJECT);
        CamelExchangeUtil.keyList.add(CamelExchangeConstants.TRANSFER_OBJECT);
    }

    /**
     * Moves All of the Headers into the Properties Map.
     * 
     * @param headers
     *            Headers Map
     * @param properties
     *            Properties Map
     */
    public static void backupHeaders(@Headers Map<String, Object> headers,
            @Properties Map<String, Object> properties) {

        for(String key : CamelExchangeUtil.keyList){
            if(headers.containsKey(key)){
                properties.put(key, headers.get(key));
            }
        }
    }

    /**
     * Moves All of the Properties into the Headers Map.
     * 
     * @param headers
     *            Headers Map
     * @param properties
     *            Properties Map
     */
    public static void restoreHeaders(@Headers Map<String, Object> headers,
            @Properties Map<String, Object> properties) {

        for(String key : CamelExchangeUtil.keyList){
            if(properties.containsKey(key)){
                headers.put(key, properties.get(key));
            }
        }
    }
}
