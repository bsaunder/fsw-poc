/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.common.bean;

import java.io.Serializable;

import com.redhat.fsw.poc.sy_stockquote.generated.xsd.stocktrade.StockTrade;

/**
 * Bean used for Transfering Data between flows via JMS.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class TransferBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private StockTrade manifest;
    private Throwable exception;
    private String errorMessage;
    private String errorCode;

    /**
     * Get the manifest.
     * 
     * @return the manifest
     */
    public StockTrade getManifest() {
        return this.manifest;
    }

    /**
     * Set the manifest.
     * 
     * @param manifest
     *            the manifest to set
     */
    public void setManifest(StockTrade manifest) {
        this.manifest = manifest;
    }

    /**
     * Get the exception.
     * 
     * @return the exception
     */
    public Throwable getException() {
        return this.exception;
    }

    /**
     * Set the exception.
     * 
     * @param exception
     *            the exception to set
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
     * Get the errorMessage.
     * 
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Set the errorMessage.
     * 
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Get the errorCode.
     * 
     * @return the errorCode
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Set the errorCode.
     * 
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
