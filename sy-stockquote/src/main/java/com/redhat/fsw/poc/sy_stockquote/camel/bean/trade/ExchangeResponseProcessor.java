/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.trade;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stockdata.GetQuoteResponse;

/**
 * Processes the Response from the Stock Exchange.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ExchangeResponseProcessor {
    
    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Processes the Response from the Stock Exchange.
     * @param headers Exchange Headers
     * @param response Exchange Body with Response
     */
    @Handler
    public void processResponse(@Headers Map<String,Object> headers, @Body GetQuoteResponse response){
        this.logger.info("Exchange Response Processor Started");
                
        if(response != null){
            String quoteResult = response.getGetQuoteResult();
            
            // If Contains Last Price of 0.0 then It is Invalid
            if(quoteResult.contains("<Last>0.00</Last>")){
                headers.put(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID, false);
            }else{
                headers.put(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID, true);
            }
        }else{
            this.logger.error("State Exchange Response was null, Setting to False");
            headers.put(CamelExchangeConstants.IS_STATE_EXCHANGE_VALID, false);
        }
        
        this.logger.info("Exchange Response Processor Completed");
    }
}
