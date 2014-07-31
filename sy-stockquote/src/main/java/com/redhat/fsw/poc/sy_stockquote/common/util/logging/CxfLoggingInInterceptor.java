/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.common.util.logging;

import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CXF Interceptor for Logging Inbound XML Requests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class CxfLoggingInInterceptor extends AbstractSoapInterceptor {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CxfLoggingInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        try {
            // Get the Request XML
            InputStream is = message.getContent(InputStream.class);
            CachedOutputStream os = new CachedOutputStream();
            IOUtils.copy(is, os);

            os.flush();
            message.setContent(InputStream.class, os.getInputStream());
            is.close();

            String requestXML = IOUtils.toString(os.getInputStream());
            os.close();

            // Log the Request XML
            this.logger.info("Incoming SOAP Request (RAW): \n" + requestXML);
        } catch (IOException ex) {
            this.logger.error("Error Logging Incoming SOAP Request, " + ex.getMessage(), ex);
        }

    }
}
