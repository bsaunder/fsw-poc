/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.trade;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeUtil;
import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;
import com.redhat.fsw.poc.sy_stockquote.trade.TradeConstants;

/**
 * Manifest Builder for Stock Trades.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TradeManifestBuilder {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Builds a Manifest based on the Headers provided by the incoming Exchange and Places it on the Exchange Message as
     * a Header.
     * 
     * @param exchange
     *            Incoming Camel Exchange
     */
    @Handler
    public void buildManifest(Exchange exchange) {
        this.logger.info("Trade Manifest Builder Started");

        // Get the Header for the Manifest with its XML Content
        Object manifestHeader = CamelExchangeUtil.getHeader(exchange, TradeConstants.TRADE_MANIFEST_HEADER_NAME);
        if (manifestHeader != null) {
            String manifestString = (String) manifestHeader;

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Preparing to Unmarshall Manifest: " + manifestString);
            }

            // Unmarshall Manifest
            try {
                // If there is a Missing Field, or a Field is Invalid, it will simply set it to Null.
                JAXBContext jaxbContext = JAXBContext
                        .newInstance("com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade");
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StringBuffer xmlStr = new StringBuffer(manifestString);
                StockTrade stockTrade = (StockTrade) unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr
                        .toString())));

                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("StockTrade: " + stockTrade.getAccountId() + " - " + stockTrade.getAmount()
                            + " - " + stockTrade.getSymbol() + " - " + stockTrade.getType());
                }

                // Set Manifest on Exchange
                CamelExchangeUtil.setInHeader(exchange, CamelExchangeConstants.MANIFEST_OBJECT, stockTrade);

            } catch (JAXBException jaxbe) {
                this.logger.error("Could Not Unmarshall StockTrade, " + jaxbe.getMessage(), jaxbe);
                CamelExchangeUtil.setInHeader(exchange, CamelExchangeConstants.IS_MANIFEST_VALID, false);
            }

        } else {
            CamelExchangeUtil.setInHeader(exchange, CamelExchangeConstants.IS_MANIFEST_VALID, false);
        }

        this.logger.info("Trade Manifest Builder Completed");
    }

}
