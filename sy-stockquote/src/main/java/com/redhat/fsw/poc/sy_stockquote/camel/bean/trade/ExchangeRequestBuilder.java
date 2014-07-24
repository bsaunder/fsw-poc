/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.trade;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuote;
import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;

/**
 * Builds the Objects needed to make SOAP Calls to the Stock Exchange.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ExchangeRequestBuilder {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Builds a SOAP Request to call the Stock Exchange
     * 
     * @param exchange
     *            Incoming Exchange
     */
    @Handler
    public void buildStockExchangeRequest(Exchange exchange) {
        this.logger.info("Stock Exchange Request Builder Started");

        // Get Manifest from Header
        Object manifestObject = exchange.getIn().getHeader(CamelExchangeConstants.MANIFEST_OBJECT);
        if (manifestObject instanceof StockTrade) {
            StockTrade manifest = (StockTrade) manifestObject;

            // Get Symbol
            String symbol = manifest.getSymbol();

            // Build Request
            GetQuote request = new GetQuote();
            request.setSymbol(symbol);

            // Set Request on Body
            exchange.getIn().setBody(request);

        } else {
            throw new IllegalStateException("Manifest Object invalid format, StockTrade expected.");
        }

        this.logger.info("Stock Exchange Request Builder Completed");
    }
}
