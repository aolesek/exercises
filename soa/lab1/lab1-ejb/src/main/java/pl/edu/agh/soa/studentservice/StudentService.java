package pl.edu.agh.soa.studentservice;

import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;
import pl.edu.agh.soa.model.Student;
import pl.edu.agh.soa.model.StudentBuilder;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService
@RolesAllowed("user")
@SecurityDomain("other")
@WebContext(contextRoot = "/my-soap", urlPattern = "/students/*", authMethod = "BASIC", transportGuarantee = "NONE", secureWSDLAccess = false)
public class StudentService {

    private List<Student> students;

    public StudentService() {
        String[] courses = {"SOA", "Kompilatory", "Systemy Wbudowane"};

        Student s1 = StudentBuilder.aStudent()
                .firstName("Jan")
                .lastName("Kowalski")
                .studentId("2352365")
                .courses(Arrays.asList(courses))
                .build();

        Student s2 = StudentBuilder.aStudent()
                .firstName("Michał")
                .lastName("Żebro")
                .studentId("23452111")
                .courses(Arrays.asList(courses))
                .avatar("c:/avatar2.png")
                .build();

        Student s3 = StudentBuilder.aStudent()
                .firstName("Anna")
                .lastName("Joanna VI")
                .studentId("23124532111")
                .courses(Arrays.asList(courses))
                .avatar("c:/avatar1.png")
                .build();

        Student s4 = StudentBuilder.aStudent()
                .firstName("Anna")
                .lastName("Joanna II")
                .studentId("234532111")
                .courses(Arrays.asList(courses))
                .avatar("c:/avatar1.png")
                .build();

        Student s5 = StudentBuilder.aStudent()
                .firstName("Janina")
                .lastName("Kowalska")
                .studentId("66634322")
                .courses(Arrays.asList(courses))
                .avatar("c:/avatar1.png")
                .build();

        Student s6 = StudentBuilder.aStudent()
                .firstName("Michał")
                .lastName("Kowalski")
                .studentId("66633334322")
                .courses(Arrays.asList(courses))
                .avatar("c:/avatar1.png")
                .build();

        students = Arrays.asList(s1, s2, s3, s4, s5, s6);
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    @WebMethod(action = "list_all_students")
    @WebResult(name = "StudentList")
    @PermitAll
    public List<Student> getStudents() {
        return students;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    @WebMethod(action = "filter_students")
    @WebResult(name = "FilteredStudentList")
    @PermitAll
    public List<Student> getFilteredStudents(@WebParam(name = "phrase") String phrase) {
        phrase = phrase.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase();
        List<String> tokens = Arrays.asList(phrase.trim().split(" "));

        String phrasePattern = "(?s)^";
        for (String token : tokens)
            phrasePattern += "(?=.*?" + token + ")";

        final String finalPattern = phrasePattern;
        List<Student> filteredStudents = students.stream().filter(t -> t.toString().toLowerCase().matches(".*" + finalPattern + ".*")).collect(Collectors.toList());
        return filteredStudents;
    }

    @WebMethod(action = "get_student_by_id")
    @WebResult(name = "SingleStudent")
    @PermitAll
    public Student getStudentById(@WebParam(name = "studentId") String id) {
        List<Student> studentsWithId = students.stream().filter(t -> t.getStudentId().equals(id)).collect(Collectors.toList());
        if (studentsWithId.isEmpty())
            throw new IllegalArgumentException();
        return studentsWithId.get(0);
    }

    @WebMethod(action = "get_avatar")
    @WebResult(name = "Avatar")
    @PermitAll
    public byte[] getAvatarById(@WebParam(name = "studentId") String id) {
        List<Student> studentsWithId = students.stream().filter(t -> t.getStudentId().equals(id)).collect(Collectors.toList());
        if (studentsWithId.isEmpty())
            throw new IllegalArgumentException("Student with provided id does not exist");
        if (studentsWithId.get(0).getAva() == null)
            throw new WebApplicationException("Student does not have an avatar!");
        return studentsWithId.get(0).getAva();
    }
}
