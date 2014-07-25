SOAP Camel JMS
=======

The SOAP Camel JMS project uses a single SOAP Endpoint that consists of one operation (getQuote). This single operation, when called, computers the price of the given Stock Symbol and places it on a JMS Queue. It also returns the Price back as a SOAP Response. An additional JMS Consumer pulls the message from the Queue and Logs it.

Design
======
TODO

Build/Deploy/Test
======
Start FSW in standalone mode

    ${FSW_HOME}/bin/stanalone.sh

Build and deploy the poc

	mvn install -Pdeploy

Run the test client

	mvn exec:java

You should see the following output

	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	   <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"/>
	   <soap:Body>
	      <GetQuoteResponse xmlns="urn:com.redhat.fsw.poc:soap-camel-jms:1.0">
	         <GetQuoteResult>55.00</GetQuoteResult>
	      </GetQuoteResponse>
	   </soap:Body>
	</soap:Envelope>

Undeploy the poc

	mvn clean -Pdeploy


Functional Testing
======
Once Deployed, The project can be tested using the SoapUI Project provided in *src/test/resources*
