package com.redhat.fsw.poc.sy_stockquote.camel.route.trade;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.redhat.fsw.poc.sy_stockquote.camel.bean.common.StockTradeResponseBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.ExchangeRequestBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.ExchangeResponseProcessor;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.TradeManifestBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.TradeManifestValidator;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuote;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuoteResponse;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stocktrader.StockTradeOutput;

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
    public static final String inboundTradeRouteId = "InboundTradeRoute";

    /**
     * The Camel route(s) are configured via this method.  The from endpoint is required to be a SwitchYard service.
     * @throws JAXBException 
     */
    public void configure() throws JAXBException {
        
        // JAXB Data Formatter
        JaxbDataFormat stockTradeOutputJaxb = new JaxbDataFormat(JAXBContext.newInstance(StockTradeOutput.class.getPackage().getName()));
        stockTradeOutputJaxb.setFragment(true);
        
        JaxbDataFormat exchangeRequestJaxb = new JaxbDataFormat(JAXBContext.newInstance(GetQuote.class.getPackage().getName()));
        exchangeRequestJaxb.setFragment(true);
        
        JaxbDataFormat exchangeResponseJaxb = new JaxbDataFormat(JAXBContext.newInstance(GetQuoteResponse.class.getPackage().getName()));
        exchangeResponseJaxb.setFragment(true);
        
        // Predicate Magic
        // Ref: http://www.davsclaus.com/2009/02/apache-camel-and-using-compound.html
        Predicate manifestValidPredicate = header(CamelExchangeConstants.IS_MANIFEST_VALID).isEqualTo(true);
        Predicate stateExchangeValidPredicate = header(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID).isEqualTo(true);
        Predicate manifestAndStateExchangeValidPredicate = PredicateBuilder.and(manifestValidPredicate, stateExchangeValidPredicate);
        
        // Inbound Stock Trading Route
        from("switchyard://StockServicePortType")
        .routeId(inboundTradeRouteId)
        .log("Received message for 'StockServicePortType'")
        .log(inboundTradeRouteId + ": Route Started")
        .bean(TradeManifestBuilder.class)
        .choice()
            .when(header(CamelExchangeConstants.MANIFEST_OBJECT).isNotNull())
                .log(inboundTradeRouteId + ": Manifest Created")
                .bean(TradeManifestValidator.class)
                .choice()
                    .when(manifestValidPredicate)
                        .log(inboundTradeRouteId + ": Manifest Valid, Calling Exchange")
                        .bean(ExchangeRequestBuilder.class)
                        .marshal(exchangeRequestJaxb).convertBodyTo(String.class)
                        .log(LoggingLevel.INFO, "Calling Stock Data Service, Request: ${body}")
                        .log("IN-MV4: ${in.header.ST_isManifestValid}")
                        .to("switchyard://StockQuoteCaller") // To() Kills my Headers... I am getting a New Exchange
                        .log("IN-MV5: ${in.header.ST_isManifestValid}")
                        .log(LoggingLevel.INFO, "Called Stock Data Service, Returned: ${body}")
                        .unmarshal(exchangeResponseJaxb)
                        .bean(ExchangeResponseProcessor.class)
                        .log("IN-MV6: ${in.header.ST_isManifestValid}")
                    .endChoice() // Terminate inner choice()
            .end() // Terminate choice()
        .choice()
            .when(manifestAndStateExchangeValidPredicate)
                .log(inboundTradeRouteId + ": State Exchange & Manifest Valid")
                // TODO bean(EPDAttachmentHandler, inflateAttachment)
                // TODO to(file://NFS_Share)
                .to("switchyard://FileRejectionSender")
            .otherwise()
                .log("IN-MV7: ${in.header.ST_isManifestValid}")
                .log(inboundTradeRouteId + ": State Exchange Or Manifest Not Valid")
                .to("switchyard://ESBNotifySender")
            .end() // Terminate choice()
        .log(inboundTradeRouteId + ": Building Response")
        .log("IN-MV8: ${in.header.ST_isManifestValid}")
        .bean(StockTradeResponseBuilder.class)
        .log("IN-MV9: ${in.header.ST_isManifestValid}")
        .marshal(stockTradeOutputJaxb)
        .convertBodyTo(String.class)
        .log(inboundTradeRouteId + ": Route Completed");
        
    }

}
