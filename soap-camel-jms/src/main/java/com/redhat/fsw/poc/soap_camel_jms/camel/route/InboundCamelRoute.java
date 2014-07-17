package com.redhat.fsw.poc.soap_camel_jms.camel.route;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.redhat.fsw.poc.soap_camel_jms.bean.PriceBean;
import com.redhat.fsw.poc.soap_camel_jms.generated.StockQuoteSoap;

public class InboundCamelRoute extends RouteBuilder {

    /**
     * The Camel route is configured via this method. The from endpoint is required to be a SwitchYard service.
     * @throws JAXBException 
     */
    public void configure() throws JAXBException {
        
        JaxbDataFormat jxb = new JaxbDataFormat(JAXBContext.newInstance(StockQuoteSoap.class.getPackage().getName()));
        jxb.setFragment(true);

        from("switchyard://StockQuoteSoap")
        .log("Received message for 'StockQuoteSoap' : ${body}")
        // Unmarshall the SOAP Body
        .unmarshal(jxb)
        // Set the Price and Build the Response
        .bean(PriceBean.class, "setPrice")
        // Marshall the Result Object Back to XML
        .marshal(jxb).convertBodyTo(String.class);
    }

}
