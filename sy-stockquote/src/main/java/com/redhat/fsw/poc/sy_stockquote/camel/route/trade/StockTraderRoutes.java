package com.redhat.fsw.poc.sy_stockquote.camel.route.trade;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.TradeManifestBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;

/**
 * Main Camel Route Builder for configuring all of the Stock Trader related Routes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class StockTraderRoutes extends RouteBuilder {
    
    /**
     * Inbound Trade Route ID.
     */
    public static final String inboundTradeRouteId ="InboundTradeRoute";

    /**
     * The Camel route(s) are configured via this method.  The from endpoint is required to be a SwitchYard service.
     */
    public void configure() {
        
        // Inbound Stock Trading Route
        from("switchyard://StockServicePortType")
        .routeId(inboundTradeRouteId)
        .log("Received message for 'StockServicePortType'")
        .log(inboundTradeRouteId + ": Route Started")
        .bean(TradeManifestBuilder.class, "buildManifest")
        .choice()
            .when(header(CamelExchangeConstants.MANIFEST_OBJECT).isNotNull())
                .log(inboundTradeRouteId + ": Manifest Created")
                // Replace with Bean call to Validator
                .setHeader(CamelExchangeConstants.IS_MANIFEST_VALID).constant(true)
                .choice()
                    .when(header(CamelExchangeConstants.IS_MANIFEST_VALID).isEqualTo(true))
                        .log(inboundTradeRouteId + ": Manifest Valid")
                        // Replace with SOAP call to State Exchange
                        .setHeader(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID).constant(true)
                        .choice()
                            .when(header(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID).isEqualTo(true))
                                .log(inboundTradeRouteId + ": State Exchange Valid")
                                //bean(EPDAttachmentHandler, inflateAttachment)
                                //to(file://NFS_Share)
                                //to(switchyard://JMS_FileRejection)
                            .otherwise()
                                .log(inboundTradeRouteId + ": State Exchange Not Valid")
                            .endChoice()
                    .otherwise()
                        .log(inboundTradeRouteId + ": Manifest Not Valid")
                    .endChoice()
            .otherwise()
                .log(inboundTradeRouteId + ": Manifest Creation Failed")
            .end() // Terminate choice()
        //to(switchyard://JMS_ESBNotify)
        // Set the Result to CMS
        .log(inboundTradeRouteId + ": Route Completed");
        
    }

}
