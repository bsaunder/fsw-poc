package com.redhat.fsw.poc.sy_stockquote.camel.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * Main Camel Route Builder for configuring all of the Stock Trader related Routes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class StockTraderRoutes extends RouteBuilder {

    /**
     * The Camel route(s) are configured via this method.  The from endpoint is required to be a SwitchYard service.
     */
    public void configure() {
        
        // Inbound Stock Trading Route
        from("switchyard://StockServicePortType")
        .log("Received message for 'StockServicePortType' : ${body}");
    }

}
