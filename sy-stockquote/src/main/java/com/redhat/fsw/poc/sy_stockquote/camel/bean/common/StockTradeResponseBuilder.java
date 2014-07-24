package com.redhat.fsw.poc.sy_stockquote.camel.bean.common;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeUtil;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stocktrader.StockTradeOutput;

/**
 * Builds the Response Objects for the Stock Trade Request.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class StockTradeResponseBuilder {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Builds a StockTradeResponse Object and Sets it on the Exchange Body.
     * 
     * @param exchange
     *            Incoming Exchange
     */
    @Handler
    public void buildStockTradeResponse(Exchange exchange) {
        this.logger.info("Stock Trade Response Builder Started");

        // Get Stock Trade Manifest
        Boolean manifestValid = (Boolean) CamelExchangeUtil.getHeader(exchange,
                CamelExchangeConstants.IS_MANIFEST_VALID);
        Boolean exchangeValid = (Boolean) CamelExchangeUtil.getHeader(exchange,
                CamelExchangeConstants.IS_STATE_EXCHANGE_VALID);

        // Build Response Object
        StockTradeOutput output = new StockTradeOutput();
        output.setTradeResult(manifestValid && exchangeValid);

        // Set Body
        CamelExchangeUtil.setBody(exchange, output);

        this.logger.info("Stock Trade Response Builder Completed");
    }
}
