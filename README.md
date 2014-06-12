Fuse Service Works Proof of Concepts
=======
This project contains several smaller projects that represent multiple proof of concept (PoC) applications for use with Fuse Service Works (FSW). These projects can be used to get up and running quickly in FSW.

- **soap-helloworld** - Basic SOAP endpoint that returns Hello World from a Java bean.
- **soap-mtom** - *NYI*, Basic SOAP endpoint that accepts an XML file using MTOM and saves the file locally
- **soap-header** - *NYI*, Multiple SOAP endpoints with different WSDL's all being processed by a single processor
- **soap-jms** - *NYI*, Basic SOAP Endpoint that sends a message to a JMS Queue
- **camel-jmsconsumer** - *NYI*, A Camel based JMS Consumer that runs on Startup
- **camel-cbr** - *NYI*, A Camel based Content Based Router
- **jms-dlq-pickup** - *NYI*, A DLQ Pickup Service that takes dead messages and returns them to the processing Queue periodically
- **logging** - *NYI*, A Logging configuration that logs errors to a file separate from the rest of the logs
- **combined** - *NYI*, A Combination of all previous PoC's into a single application

Examples with *NYI* in the description are Not Yet Implemented.

Environment Requirements
======
The following items are required to be installed and properly configured in order to run the PoC's.

1. Fuse Service Works 6.0 GA
	- [Download Link](http://www.jboss.org/download-manager/file/jboss-fsw-6.0.0.GA.zip)
	- [Installation Guide](https://access.redhat.com/site/documentation/en-US/Red_Hat_JBoss_Fuse_Service_Works/6/html-single/Installation_Guide/index.html)
2. Maven 3
	- [Download Link](ftp://mirror.reverse.net/pub/apache/maven/maven-3/3.2.1/binaries/apache-maven-3.2.1-bin.zip)
	- [Installation Guide](http://maven.apache.org/download.cgi#Installation)
3. Git
	- [Download Link](http://git-scm.com/downloads)
	- [Usage Guide](http://git-scm.com/book/en/)

Development Environment
======
The following tools are required if you want to do SwitchYard development.

1. Fuse Service Works 6.0 GA
	- [Download Link](http://www.jboss.org/download-manager/file/jboss-fsw-6.0.0.GA.zip)
	- [Installation Guide](https://access.redhat.com/site/documentation/en-US/Red_Hat_JBoss_Fuse_Service_Works/6/html-single/Installation_Guide/index.html)
2. Maven 3
	- [Download Link](ftp://mirror.reverse.net/pub/apache/maven/maven-3/3.2.1/binaries/apache-maven-3.2.1-bin.zip)
	- [Installation Guide](http://maven.apache.org/download.cgi#Installation)
3. JBoss Developer Studio 7.1.x
	- [Download Link](http://www.jboss.org/download-manager/file/jboss-devstudio-7.1.1.GA-standalone_universal-standalone_jar.jar)
4. JBoss SwitchYard Integration Stack
	- Open JBoss Developer Studio
	- Go to Help->Jboss Central
	- Select "Software/Update" from the tabs at the bottom of the page
	- Select "JBoss Integration and SOA Development"
	- Click Install and follow Installation Wizard

Development References
=======
1. [JBoss SwitchYard Homepage](http://switchyard.jboss.org/)
2. [JBoss SwitchYard Quick Starts](https://github.com/jboss-switchyard/quickstarts)
3. [JBoss SwitchYard Video Series](https://community.jboss.org/wiki/SwitchYardVideoSeries)
4. [Fuse Service Works 6 Documentation](https://access.redhat.com/site/documentation/en-US/Red_Hat_JBoss_Fuse_Service_Works/)
5. [Red Hat FSW Development Guide: SwitchYard](https://access.redhat.com/site/documentation/en-US/Red_Hat_JBoss_Fuse_Service_Works/6/html/Development_Guide_Volume_1_SwitchYard/index.html)

Development Notes
=======
FSW/Java on Windows
-------
If you are installing FSW on a Windows machine for Development, then the location of your JDK/JRE should be at a path with no spaces. The default "Program Files" folder will cause errors in the FSW installer and start scripts. Recommended to install at the root of the C: drive, ie:* C:\jdk1.7_u60 *

Changing the SOAP Port for Unit Tests
-------
When testing SOAP services/bindings using the SwithYard Testing Framework the tests may need to run on a different port than normal. The port is defined in the *switchyard.xml* file and that port will always be used whether you are deploying the application or running a test. This can cause problems if you have the application deployed to the server and the server running, and then try to run the unit tests. Since the tests are trying to use the same port, they will fail with a Binding Exception.

To solve this problem, you can add a Domain Property with default to the SwitchYard configuration and set all port fields in the configuration to use that property. Then in your tests you can change the value of the property so that the tests run on a different port and do not try to bind to a port that is already in use.

All of the PoC's in this project do exactly that. If you look at any of the *switchyard.xml* file's, you will see this code in the xml

	<sy:domain>
		<sy:properties>
			<sy:property name="jettyPort" value="${org.switchyard.component.http.standalone.port:8080}"/>
		</sy:properties>
	</sy:domain>

and this code in the test file is what changes the port for the test.

	@BeforeDeploy
	public void setProperties() {
	    System.setProperty("org.switchyard.component.http.standalone.port", "8081");
	}