package pl.edu.agh.soa.studentservice;

import org.jboss.ws.api.annotation.WebContext;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@WebService
@WebContext(contextRoot="/my-soap", urlPattern="/public/*", transportGuarantee="NONE", secureWSDLAccess=false)
public class HelloWorld {
    @WebMethod(action = "say_hi")
    @WebResult(name = "HelloResponse")
    public String hello(@WebParam(name = "name") String name) {
        return "Hello Hello " + name;
    }
}
