/**
 * 
 */
package com.redhat.fsw.poc.soap_helloworld.transformer;

import java.io.StringReader;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public final class HelloWorldTransformer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transformer(to = "{urn:com.redhat.fsw.poc:soap-helloworld:1.0}sayHelloWorldResponse")
    public Element transformStringToSayHelloWorldResponse(String from) {

        this.logger.info("Transforming String '" + from + "' to sayHelloWorldResponse");

        StringBuffer ackXml = new StringBuffer()
                .append("<hello:sayHelloResponse xmlns:hello=\"urn:com.redhat.fsw.poc:soap-helloworld:1.0\">")
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
