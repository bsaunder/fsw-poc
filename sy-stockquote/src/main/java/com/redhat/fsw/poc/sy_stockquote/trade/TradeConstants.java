package com.redhat.fsw.poc.sy_stockquote.trade;

/**
 * Stock Trade Related Constants.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public abstract class TradeConstants {
    
    public static final String TRADE_MANIFEST_HEADER_NAME = "{urn:com.redhat.fsw.poc:sy-stockquote:1.0}stockTrade";

    /**
     * Private Constructor. Class Should Not Be Instantiated.
     */
    private TradeConstants() {
        // Don't Instantiate Me Bro!
    }
}
