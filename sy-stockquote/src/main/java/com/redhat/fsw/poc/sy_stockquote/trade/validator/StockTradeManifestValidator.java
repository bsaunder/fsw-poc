/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.trade.validator;

import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;

/**
 * Responsible for Validating a the StockTrade Manifest for the Trade Flows.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class StockTradeManifestValidator {

    /**
     * Checks that the given StockTrade is a valid Manifest.
     * 
     * @param manifest
     *            StockTrade to Validate
     * @return false if invalid, true otherwise
     */
    public boolean validateManifest(StockTrade manifest) {
        boolean valid = true;

        // Test the Type, Should be a B or an S
        if (!manifest.getType().matches("[BS]{1}")) {
            valid = false;
        }

        return valid;
    }

}
