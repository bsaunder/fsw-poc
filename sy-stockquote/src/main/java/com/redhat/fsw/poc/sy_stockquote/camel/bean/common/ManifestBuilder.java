/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.common;

import org.apache.camel.Exchange;

/**
 * Defines a Manifest Builder.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public interface ManifestBuilder {

    /**
     * Builds a Manifest based on the Headers provided by the incoming Exchange and Places it on the Exchange Message as
     * a Header.
     * 
     * @param exchange
     *            Incoming Camel Exchange
     */
    public void buildManifest(Exchange exchange);
}
