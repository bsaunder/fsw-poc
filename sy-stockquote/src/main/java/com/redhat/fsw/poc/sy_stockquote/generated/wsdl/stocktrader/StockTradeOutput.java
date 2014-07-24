
package com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stocktrader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tradeResult"
})
@XmlRootElement(name = "StockTradeOutput")
public class StockTradeOutput {

    protected boolean tradeResult;

    /**
     * Gets the value of the tradeResult property.
     * 
     */
    public boolean isTradeResult() {
        return tradeResult;
    }

    /**
     * Sets the value of the tradeResult property.
     * 
     */
    public void setTradeResult(boolean value) {
        this.tradeResult = value;
    }

}
