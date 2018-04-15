
package pl.edu.agh.soa.studentservice.consumer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.edu.agh.soa.studentservice.consumer package. 
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

    private final static QName _GetAvatarById_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getAvatarById");
    private final static QName _GetAvatarByIdResponse_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getAvatarByIdResponse");
    private final static QName _GetFilteredStudents_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getFilteredStudents");
    private final static QName _GetFilteredStudentsResponse_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getFilteredStudentsResponse");
    private final static QName _GetStudentById_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getStudentById");
    private final static QName _GetStudentByIdResponse_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getStudentByIdResponse");
    private final static QName _GetStudents_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getStudents");
    private final static QName _GetStudentsResponse_QNAME = new QName("http://studentservice.soa.agh.edu.pl/", "getStudentsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.edu.agh.soa.studentservice.consumer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link GetStudentsResponse }
     * 
     */
    public GetStudentsResponse createGetStudentsResponse() {
        return new GetStudentsResponse();
    }

    /**
     * Create an instance of {@link GetAvatarById }
     * 
     */
    public GetAvatarById createGetAvatarById() {
        return new GetAvatarById();
    }

    /**
     * Create an instance of {@link GetAvatarByIdResponse }
     * 
     */
    public GetAvatarByIdResponse createGetAvatarByIdResponse() {
        return new GetAvatarByIdResponse();
    }

    /**
     * Create an instance of {@link GetFilteredStudents }
     * 
     */
    public GetFilteredStudents createGetFilteredStudents() {
        return new GetFilteredStudents();
    }

    /**
     * Create an instance of {@link GetFilteredStudentsResponse }
     * 
     */
    public GetFilteredStudentsResponse createGetFilteredStudentsResponse() {
        return new GetFilteredStudentsResponse();
    }

    /**
     * Create an instance of {@link GetStudentById }
     * 
     */
    public GetStudentById createGetStudentById() {
        return new GetStudentById();
    }

    /**
     * Create an instance of {@link GetStudentByIdResponse }
     * 
     */
    public GetStudentByIdResponse createGetStudentByIdResponse() {
        return new GetStudentByIdResponse();
    }

    /**
     * Create an instance of {@link GetStudents }
     * 
     */
    public GetStudents createGetStudents() {
        return new GetStudents();
    }

    /**
     * Create an instance of {@link Student.Courses }
     * 
     */
    public Student.Courses createStudentCourses() {
        return new Student.Courses();
    }

    /**
     * Create an instance of {@link GetStudentsResponse.Students }
     * 
     */
    public GetStudentsResponse.Students createGetStudentsResponseStudents() {
        return new GetStudentsResponse.Students();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvatarById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getAvatarById")
    public JAXBElement<GetAvatarById> createGetAvatarById(GetAvatarById value) {
        return new JAXBElement<GetAvatarById>(_GetAvatarById_QNAME, GetAvatarById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvatarByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getAvatarByIdResponse")
    public JAXBElement<GetAvatarByIdResponse> createGetAvatarByIdResponse(GetAvatarByIdResponse value) {
        return new JAXBElement<GetAvatarByIdResponse>(_GetAvatarByIdResponse_QNAME, GetAvatarByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilteredStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getFilteredStudents")
    public JAXBElement<GetFilteredStudents> createGetFilteredStudents(GetFilteredStudents value) {
        return new JAXBElement<GetFilteredStudents>(_GetFilteredStudents_QNAME, GetFilteredStudents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilteredStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getFilteredStudentsResponse")
    public JAXBElement<GetFilteredStudentsResponse> createGetFilteredStudentsResponse(GetFilteredStudentsResponse value) {
        return new JAXBElement<GetFilteredStudentsResponse>(_GetFilteredStudentsResponse_QNAME, GetFilteredStudentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getStudentById")
    public JAXBElement<GetStudentById> createGetStudentById(GetStudentById value) {
        return new JAXBElement<GetStudentById>(_GetStudentById_QNAME, GetStudentById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getStudentByIdResponse")
    public JAXBElement<GetStudentByIdResponse> createGetStudentByIdResponse(GetStudentByIdResponse value) {
        return new JAXBElement<GetStudentByIdResponse>(_GetStudentByIdResponse_QNAME, GetStudentByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getStudents")
    public JAXBElement<GetStudents> createGetStudents(GetStudents value) {
        return new JAXBElement<GetStudents>(_GetStudents_QNAME, GetStudents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://studentservice.soa.agh.edu.pl/", name = "getStudentsResponse")
    public JAXBElement<GetStudentsResponse> createGetStudentsResponse(GetStudentsResponse value) {
        return new JAXBElement<GetStudentsResponse>(_GetStudentsResponse_QNAME, GetStudentsResponse.class, null, value);
    }

}
