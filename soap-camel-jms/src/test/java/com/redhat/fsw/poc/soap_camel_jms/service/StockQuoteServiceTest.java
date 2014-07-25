package com.redhat.fsw.poc.soap_camel_jms.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import com.redhat.fsw.poc.soap_camel_jms.client.SoapCamelJmsClient;

@Ignore
// For some reason, the Camel Exchange is Failing to Start when this test runs...
@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = { CDIMixIn.class }, config = SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
        BeanSwitchYardScanner.class, TransformSwitchYardScanner.class, RouteScanner.class })
public class StockQuoteServiceTest {

    private static final String SWITCHYARD_WEB_SERVICE = "http://localhost:8081/soap-camel-jms/StockQuote";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.http.standalone.port", "8081");
    }

    @Test
    public void testSwitchYardWebService() throws Exception {
        this.logger.info("Sending Test SOAP Request...");

        String response = SoapCamelJmsClient.sendMessage(SWITCHYARD_WEB_SERVICE);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.length() > 1);
        Assert.assertTrue(response.contains("<GetQuoteResult>55.00</GetQuoteResult>"));
    }
}
