package com.redhat.fsw.poc.sy_stockquote.camel.bean.trade;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeUtil;
import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;
import com.redhat.fsw.poc.sy_stockquote.trade.validator.StockTradeManifestValidator;

/**
 * Validates the Manifest for the Trade Flow.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class TradeManifestValidator {
    
    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Validates a Manifest.
     * 
     * @param exchange
     *            Incoming Camel Exchange
     */
    @Handler
    public void validateManifest(Exchange exchange) throws IllegalStateException {
        this.logger.info("Trade Manifest Validator Started");
        
        // Get Manifest from Header
        Object manifestObject = CamelExchangeUtil.getHeader(exchange, CamelExchangeConstants.MANIFEST_OBJECT);
        if(manifestObject instanceof StockTrade){
            StockTrade manifest = (StockTrade) manifestObject;
            
            // Validate Manifest
            StockTradeManifestValidator validator = new StockTradeManifestValidator();
            boolean isValid = validator.validateManifest(manifest);
            
            // Set Headers with Result
            CamelExchangeUtil.setInHeader(exchange, CamelExchangeConstants.IS_MANIFEST_VALID, isValid);
        }else{
            throw new IllegalStateException("Manifest Object invalid format, StockTrade expected.");
        }
        
        
        this.logger.info("Trade Manifest Validator Completed");
    }

}
