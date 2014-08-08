package com.redhat.fsw.poc.sy_stockquote.camel.util;

/**
 * Camel Exchange Related Constants.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public abstract class CamelExchangeConstants {
    
    /**
     * Specifies whether the Manifest is Valid. Should always be a boolean type.
     */
    public static final String IS_MANIFEST_VALID = "ST_isManifestValid";
    
    /**
     * Contains the Manifest for the Flow. Will always be an Object.
     */
    public static final String MANIFEST_OBJECT = "ST_manifestObject";

    /**
     * Specifies whether the Manifest is Valid. Should always be a boolean type.
     */
    public static final String IS_STATE_EXCHANGE_VALID = "ST_isStateExchangeValid";

    /**
     * Contains the Incoming Message Body.
     */
    public static final String INCOMING_BODY = "ST_incomingMessageBody";
    
    /**
     * Contains the JMS Transfer Object.
     */
    public static final String TRANSFER_OBJECT = "ST_transferObject";

    /**
     * Private Constructor. Class Should Not Be Instantiated.
     */
    private CamelExchangeConstants() {
        // Don't Instantiate Me Bro!
    }
}
