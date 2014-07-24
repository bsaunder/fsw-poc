SwitchYard StockQuote
=======

The SwitchYard StockQuote is intended to combine multiple PoC's from this project into a single project. It is designed to (very poorly) mimic a Stock Trading application of some type. A SOAP Request is made with an XML File that contains Stock Data (via MTOM/XOP) and some Meta Data in the SOAP Header. This information is then parsed, validated, verified against a Stock Quote service for correctness, and then passed onto one of two JMS Queues based on the validation results.

Design
======
This PoC consists of a main Camel Route that contains the logic for the primary flow and two supplementary routes that listen to the JMS Queues. On the main Route there is 3 references, two for JMS, and one for the SOAP Web Service call. All of the Camel Logic is seperated out from the actual business logic classes whenever possible.

Build/Deploy/Test
======
Start FSW in standalone mode

    ${FSW_HOME}/bin/stanalone.sh

Build and deploy the poc

	mvn install -Pdeploy

Run the test client

	mvn exec:java

You should see the following output

    TBD

Undeploy the poc

	mvn clean -Pdeploy


Functional Testing
======
Once Deployed, The project can be tested using the SoapUI Project provided in *src/test/resources*
