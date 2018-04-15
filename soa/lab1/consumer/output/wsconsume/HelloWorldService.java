package wsconsume;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2018-04-05T19:43:00.416+02:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "HelloWorldService", 
                  wsdlLocation = "http://localhost:8080/my-cxt?wsdl",
                  targetNamespace = "http://hello.soa.agh.edu.pl/")
public class HelloWorldService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://hello.soa.agh.edu.pl/", "HelloWorldService");
    public final static QName HelloWorldPort = new QName("http://hello.soa.agh.edu.pl/", "HelloWorldPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/my-cxt?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(HelloWorldService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/my-cxt?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public HelloWorldService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HelloWorldService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWorldService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public HelloWorldService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public HelloWorldService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public HelloWorldService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWorldPort")
    public HelloWorld getHelloWorldPort() {
        return super.getPort(HelloWorldPort, HelloWorld.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWorld
     */
    @WebEndpoint(name = "HelloWorldPort")
    public HelloWorld getHelloWorldPort(WebServiceFeature... features) {
        return super.getPort(HelloWorldPort, HelloWorld.class, features);
    }

}
