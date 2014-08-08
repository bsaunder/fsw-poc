/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.camel.bean.common;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.fsw.poc.sy_stockquote.camel.util.CamelExchangeConstants;
import com.redhat.fsw.poc.sy_stockquote.common.bean.TransferBean;
import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;

/**
 * Builds the Transfer Beans.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TransferBeanBuilder {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Builds a Transfer Object for use with JMS.
     * 
     * @param exchange
     *            Incoming Exchange
     */
    @Handler
    public void buildTransferBean(Exchange exchange) {
        this.logger.info("Transfer Bean Builder Started");

        TransferBean bean = new TransferBean();
        exchange.getIn().setHeader(CamelExchangeConstants.TRANSFER_OBJECT, bean);

        this.logger.info("Transfer Bean Builder Completed");
    }

    /**
     * Sets the Manifest on the TransferBean.
     * 
     * @param manifest
     *            Manifest
     * @param bean
     *            Transfer Bean.
     */
    public void setManifest(@Header(CamelExchangeConstants.MANIFEST_OBJECT) StockTrade manifest,
            @Header(CamelExchangeConstants.TRANSFER_OBJECT) TransferBean bean) {
        bean.setManifest(manifest);
        this.logger.info("Transfer Bean Builder Set Manifest");
    }

}
