/**
 * 
 */
package com.redhat.fsw.poc.soap_camel_jms.client;

import org.switchyard.component.soap.util.SOAPUtil;
import org.switchyard.component.test.mixins.http.HTTPMixIn;

/**
 * Client for SOAP.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 */
public class SoapCamelJmsClient {

    private static final String SWITCHYARD_WEB_SERVICE = "http://localhost:8080/soap-camel-jms/StockQuote";

    private static String SOAP_TEST_MSG = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:com.redhat.fsw.poc:soap-camel-jms:1.0\">"
            + "  <soapenv:Header/>"
            + "  <soapenv:Body>"
            + "     <urn:GetQuote>"
            + "        <urn:symbol>RHT</urn:symbol>"
            + "     </urn:GetQuote>"
            + "   </soapenv:Body>"
            + "</soapenv:Envelope>";

    public static void main(String[] args) throws Exception {
        SOAPUtil.prettyPrint(sendMessage(SWITCHYARD_WEB_SERVICE), System.out);
    }

    public static String sendMessage(String switchyard_web_service) throws Exception {
        HTTPMixIn http = new HTTPMixIn();
        http.setDumpMessages(true);
        http.initialize();

        String result = http.postString(switchyard_web_service, SOAP_TEST_MSG);

        http.uninitialize();
        return result;
    }
}
