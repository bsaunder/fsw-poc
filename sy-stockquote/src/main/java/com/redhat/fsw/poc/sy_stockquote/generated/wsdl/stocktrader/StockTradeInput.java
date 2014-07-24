
package com.redhat.fsw.poc.sy_stockquote.generated.wsdl.stocktrader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.Source;


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
 *         &lt;element name="tradeDetails" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "tradeDetails"
})
@XmlRootElement(name = "StockTradeInput")
public class StockTradeInput {

    @XmlElement(required = true)
    @XmlMimeType("text/xml")
    protected Source tradeDetails;

    /**
     * Gets the value of the tradeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getTradeDetails() {
        return tradeDetails;
    }

    /**
     * Sets the value of the tradeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setTradeDetails(Source value) {
        this.tradeDetails = value;
    }

}
