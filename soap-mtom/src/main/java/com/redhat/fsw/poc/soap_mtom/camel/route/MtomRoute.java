/**
 * 
 */
package com.redhat.fsw.poc.soap_mtom.camel.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class MtomRoute extends RouteBuilder {

    /**
     * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
     */
    public void configure() {
        from("switchyard://MtomService")
        .log("Received message for 'MtomService' : ${body}");
    }

}
