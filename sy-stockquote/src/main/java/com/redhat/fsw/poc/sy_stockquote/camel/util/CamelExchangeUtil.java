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

    /**
     * Sets the In Header to the Specified Value on the Given Exchange
     * 
     * @param exchange
     *            Exchange to Set Header On
     * @param headerName
     *            Header Name
     * @param value
     *            Header Value
     */
    public static void setInHeader(Exchange exchange, String headerName, Object value) {
        exchange.getIn().setHeader(headerName, value);
    }

    /**
     * Sets the Out Header to the Specified Value on the Given Exchange
     * 
     * @param exchange
     *            Exchange to Set Header On
     * @param headerName
     *            Header Name
     * @param value
     *            Header Value
     */
    public static void setOutHeader(Exchange exchange, String headerName, Object value) {
        exchange.getOut().setHeader(headerName, value);
    }

    /**
     * Sets the In Body to the Specified Value.
     * 
     * @param exchange
     *            Exchange to Set Body On
     * @param value
     *            Body Value
     */
    public static void setBody(Exchange exchange, Object value) {
        exchange.getIn().setBody(value);
    }
}
