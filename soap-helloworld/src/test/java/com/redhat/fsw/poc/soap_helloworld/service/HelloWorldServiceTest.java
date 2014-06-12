package com.redhat.fsw.poc.soap_helloworld.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import com.redhat.fsw.poc.soap_helloworld.client.SoapHelloWorldClient;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = { CDIMixIn.class }, config = SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
        BeanSwitchYardScanner.class, TransformSwitchYardScanner.class })
public class HelloWorldServiceTest {

    private static final String SWITCHYARD_WEB_SERVICE = "http://localhost:8081/soap-helloworld/HelloWorldService";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.http.standalone.port", "8081");
    }

    @Test
    public void testSwitchYardWebService() throws Exception {
        this.logger.info("Sending Test SOAP Request...");

        String response = SoapHelloWorldClient.sendMessage(SWITCHYARD_WEB_SERVICE);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.length() > 1);
        Assert.assertTrue(response.contains("<return>Hello World</return>"));
    }
}
