package com.redhat.fsw.poc.soap_camel_jms.bean;

import javax.inject.Named;

import org.apache.camel.Exchange;

import com.redhat.fsw.poc.soap_camel_jms.generated.GetQuote;
import com.redhat.fsw.poc.soap_camel_jms.generated.GetQuoteResponse;

@Named
public class PriceBean {
    
    public void setPrice(Exchange exchange){
        GetQuote incoming = (GetQuote) exchange.getIn().getBody();
        String symbol = incoming.getSymbol();
        
        String price = "00.00";
        
        switch(symbol){
            case "RHT":
                price = "55.00";
                break;
            case "MSFT":
                price = "44.00";
                break;
            case "GOOGL":
                price = "590.00";
                break;
        }
        
        GetQuoteResponse response = new GetQuoteResponse();
        response.setGetQuoteResult(price);
        
        exchange.getIn().setBody(response);
    }

}
