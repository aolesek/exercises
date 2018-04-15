package wsconsume;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2018-04-05T19:43:00.350+02:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://hello.soa.agh.edu.pl/", name = "HelloWorld")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloWorld {

    @WebMethod(action = "say_hi")
    @RequestWrapper(localName = "hello", targetNamespace = "http://hello.soa.agh.edu.pl/", className = "wsconsume.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://hello.soa.agh.edu.pl/", className = "wsconsume.HelloResponse")
    @WebResult(name = "HelloResponse", targetNamespace = "")
    public java.lang.String hello(
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );

    @WebMethod(action = "list_all_students")
    @RequestWrapper(localName = "getStudents", targetNamespace = "http://hello.soa.agh.edu.pl/", className = "wsconsume.GetStudents")
    @ResponseWrapper(localName = "getStudentsResponse", targetNamespace = "http://hello.soa.agh.edu.pl/", className = "wsconsume.GetStudentsResponse")
    @WebResult(name = "StudentList", targetNamespace = "")
    public java.util.List<wsconsume.Student> getStudents();
}