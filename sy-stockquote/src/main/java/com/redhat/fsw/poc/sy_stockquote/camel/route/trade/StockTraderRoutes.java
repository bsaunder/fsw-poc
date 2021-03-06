package com.redhat.fsw.poc.sy_stockquote.camel.route.trade;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.redhat.fsw.poc.sy_stockquote.camel.bean.common.StockTradeResponseBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.common.TransferBeanBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.ExchangeRequestBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.ExchangeResponseProcessor;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.TradeManifestBuilder;
import com.redhat.fsw.poc.sy_stockquote.camel.bean.trade.TradeManifestValidator;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeUtil;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuote;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuoteResponse;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stocktrader.StockTradeInput;
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
        JaxbDataFormat stockTradeInputJaxb = new JaxbDataFormat(JAXBContext.newInstance(StockTradeInput.class.getPackage().getName()));
        stockTradeInputJaxb.setFragment(true);
        
        JaxbDataFormat stockTradeOutputJaxb = new JaxbDataFormat(JAXBContext.newInstance(StockTradeOutput.class.getPackage().getName()));
        stockTradeOutputJaxb.setFragment(true);
        
        JaxbDataFormat exchangeRequestJaxb = new JaxbDataFormat(JAXBContext.newInstance(GetQuote.class.getPackage().getName()));
        exchangeRequestJaxb.setFragment(true);
        
        JaxbDataFormat exchangeResponseJaxb = new JaxbDataFormat(JAXBContext.newInstance(GetQuoteResponse.class.getPackage().getName()));
        exchangeResponseJaxb.setFragment(true);
        
        // Predicate Magic
        Predicate manifestValidPredicate = header(CamelExchangeConstants.IS_MANIFEST_VALID).isEqualTo(true);
        Predicate stateExchangeValidPredicate = header(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID).isEqualTo(true);
        Predicate manifestAndStateExchangeValidPredicate = PredicateBuilder.and(manifestValidPredicate, stateExchangeValidPredicate);
        
        // Inbound Stock Trading Route
        from("switchyard://StockServicePortType")
        .routeId(inboundTradeRouteId)
        .log("Received message for 'StockServicePortType'")
        .log(inboundTradeRouteId + ": Route Started")
        .setProperty(CamelExchangeConstants.INCOMING_BODY, simple("${body}"))
        .bean(TransferBeanBuilder.class)
        .bean(TradeManifestBuilder.class)
        .bean(TransferBeanBuilder.class, "setManifest")
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
                        .bean(CamelExchangeUtil.class, "backupHeaders") // To() Kills my Headers. New Message Returned, So we Need to Save the Headers.
                        .to("switchyard://StockQuoteCaller") 
                        .bean(CamelExchangeUtil.class, "restoreHeaders")
                        .log(LoggingLevel.INFO, "Called Stock Data Service, Returned: ${body}")
                        .unmarshal(exchangeResponseJaxb)
                        .bean(ExchangeResponseProcessor.class)
                    .endChoice() // Terminate inner choice()
            .end() // Terminate choice()
        .choice()
            .when(manifestAndStateExchangeValidPredicate)
                .log(inboundTradeRouteId + ": State Exchange & Manifest Valid")
                .setBody(simple("${in.header.ST_incomingMessageBody}"))
                .unmarshal(stockTradeInputJaxb)
                .setBody().ognl("request.body.tradeDetails") // Set the TradeDetails Source Object to the Body
                .convertBodyTo(String.class)
                .setHeader("CamelFileName", simple("st_attachment_${id}.xml"))
                .to("file:/tmp/st_attachments")
                .setBody(simple("${in.header.ST_transferObject}"))
                .to("switchyard://FileRejectionSender")
            .otherwise()
                .log(inboundTradeRouteId + ": State Exchange Or Manifest Not Valid")
                .setBody(simple("${in.header.ST_transferObject}}"))
                .to("switchyard://ESBNotifySender")
            .end() // Terminate choice()
        .log(inboundTradeRouteId + ": Building Response")
        .bean(StockTradeResponseBuilder.class)
        .marshal(stockTradeOutputJaxb)
        .convertBodyTo(String.class)
        .log(inboundTradeRouteId + ": Route Completed");

    }

}
