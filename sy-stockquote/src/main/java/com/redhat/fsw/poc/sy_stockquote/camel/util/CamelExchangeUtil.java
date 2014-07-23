/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.util;

import java.util.Map;

import org.apache.camel.Exchange;

/**
 * Utility Class for Working with Camel Exchanges.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public abstract class CamelExchangeUtil {

    /**
     * Private Constructor. Class Should Not Be Instantiated.
     */
    private CamelExchangeUtil() {
        // Don't Instantiate Me Bro!
    }

    /**
     * Finds the Header Specified on the Given Exchange. Returns null If No Header Matches.
     * 
     * @param exchange
     *            Exchange to Search
     * @param headerName
     *            Header Name
     * @return Object Value of Exchange Header or null If Not Found
     */
    public static Object getHeader(Exchange exchange, String headerName) {
        Object result = null;

        Map<String, Object> inHeaders = exchange.getIn().getHeaders();       
        if (inHeaders.containsKey(headerName)) {
            result = inHeaders.get(headerName);
        }

        if (result == null) {
            Map<String, Object> outHeaders = exchange.getOut().getHeaders();
            if (outHeaders.containsKey(headerName)) {
                result = outHeaders.get(headerName);
            }
        }

        return result;
    }
}
