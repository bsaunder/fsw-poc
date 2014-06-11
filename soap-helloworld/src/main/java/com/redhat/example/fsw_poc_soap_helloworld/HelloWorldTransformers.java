/**
 * 
 */
package com.redhat.example.fsw_poc_soap_helloworld;

import java.io.StringReader;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class HelloWorldTransformers {

    @Transformer(to = "{urn:com.redhat.example:fsw-poc-soap-helloworld:1.0}sayHelloWorldResponse")
    public Element transformStringToSayHelloWorldResponse(String from) {
        StringBuffer ackXml = new StringBuffer()
                .append("<hello:sayHelloResponse xmlns:hello=\"urn:com.example.switchyard:switchyard-example:0.0.1-SNAPSHOT\">")
                .append("<return>" + from + "</return>").append("</hello:sayHelloResponse>");

        return toElement(ackXml.toString());
    }

    private Element toElement(String xml) {
        DOMResult dom = new DOMResult();

        try {
            TransformerFactory.newInstance().newTransformer().transform(new StreamSource(new StringReader(xml)), dom);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return ((Document) dom.getNode()).getDocumentElement();
    }
}
