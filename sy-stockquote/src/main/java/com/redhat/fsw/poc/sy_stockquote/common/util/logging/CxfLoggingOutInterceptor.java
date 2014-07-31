/**
 * 
 */
package com.redhat.fsw.poc.sy_stockquote.common.util.logging;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CXF Interceptor for Logging Outbound XML Responses.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class CxfLoggingOutInterceptor extends LoggingOutInterceptor {

    public CxfLoggingOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.cxf.interceptor.LoggingOutInterceptor#handleMessage(org.apache.cxf.message.Message)
     */
    @Override
    public void handleMessage(Message message) throws Fault {
        OutputStream out = message.getContent(OutputStream.class);
        final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(out);
        message.setContent(OutputStream.class, newOut);
        newOut.registerCallback(new LoggingCallback());
    }

    /**
     * Logging Callback for Logging Outbound Responses.
     * 
     * @author Bryan Saunders <btsaunde@gmail.com>
     *
     */
    public class LoggingCallback implements CachedOutputStreamCallback {

        /**
         * Logger
         */
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.cxf.io.CachedOutputStreamCallback#onFlush(org.apache.cxf.io.CachedOutputStream)
         */
        @Override
        public void onFlush(CachedOutputStream cos) {
            // Do Nothing!
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.cxf.io.CachedOutputStreamCallback#onClose(org.apache.cxf.io.CachedOutputStream)
         */
        @Override
        public void onClose(CachedOutputStream cos) {
            try {
                StringBuilder builder = new StringBuilder();
                cos.writeCacheTo(builder, limit);

                // Get the Outbound XML
                String responseXml = builder.toString();

                this.logger.info("Outgoing SOAP Response (RAW): \n" + responseXml);
            } catch (IOException ex) {
                this.logger.error("Error Logging Outgoing SOAP Response, " + ex.getMessage(), ex);
            }
        }
    }
}
