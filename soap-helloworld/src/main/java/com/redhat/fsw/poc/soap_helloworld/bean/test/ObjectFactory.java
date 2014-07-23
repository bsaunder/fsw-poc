
package com.redhat.fsw.poc.soap_helloworld.bean.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.redhat.fsw.poc.soap_helloworld.bean.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddNumbers3Response_QNAME = new QName("http://example.com/", "addNumbers3Response");
    private final static QName _AddNumbers3_QNAME = new QName("http://example.com/", "addNumbers3");
    private final static QName _AddNumbers2Response_QNAME = new QName("http://example.com/", "addNumbers2Response");
    private final static QName _AddNumbers_QNAME = new QName("http://example.com/", "addNumbers");
    private final static QName _AddNumbers2_QNAME = new QName("http://example.com/", "addNumbers2");
    private final static QName _AddNumbersResponse_QNAME = new QName("http://example.com/", "addNumbersResponse");
    private final static QName _AddNumbersFault_QNAME = new QName("http://example.com/", "AddNumbersFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.redhat.fsw.poc.soap_helloworld.bean.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNumbersFault }
     * 
     */
    public AddNumbersFault createAddNumbersFault() {
        return new AddNumbersFault();
    }

    /**
     * Create an instance of {@link AddNumbersResponse }
     * 
     */
    public AddNumbersResponse createAddNumbersResponse() {
        return new AddNumbersResponse();
    }

    /**
     * Create an instance of {@link AddNumbers }
     * 
     */
    public AddNumbers createAddNumbers() {
        return new AddNumbers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbers3Response")
    public JAXBElement<AddNumbersResponse> createAddNumbers3Response(AddNumbersResponse value) {
        return new JAXBElement<AddNumbersResponse>(_AddNumbers3Response_QNAME, AddNumbersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbers3")
    public JAXBElement<AddNumbers> createAddNumbers3(AddNumbers value) {
        return new JAXBElement<AddNumbers>(_AddNumbers3_QNAME, AddNumbers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbers2Response")
    public JAXBElement<AddNumbersResponse> createAddNumbers2Response(AddNumbersResponse value) {
        return new JAXBElement<AddNumbersResponse>(_AddNumbers2Response_QNAME, AddNumbersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbers")
    public JAXBElement<AddNumbers> createAddNumbers(AddNumbers value) {
        return new JAXBElement<AddNumbers>(_AddNumbers_QNAME, AddNumbers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbers2")
    public JAXBElement<AddNumbers> createAddNumbers2(AddNumbers value) {
        return new JAXBElement<AddNumbers>(_AddNumbers2_QNAME, AddNumbers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "addNumbersResponse")
    public JAXBElement<AddNumbersResponse> createAddNumbersResponse(AddNumbersResponse value) {
        return new JAXBElement<AddNumbersResponse>(_AddNumbersResponse_QNAME, AddNumbersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumbersFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/", name = "AddNumbersFault")
    public JAXBElement<AddNumbersFault> createAddNumbersFault(AddNumbersFault value) {
        return new JAXBElement<AddNumbersFault>(_AddNumbersFault_QNAME, AddNumbersFault.class, null, value);
    }

}
