package pl.edu.agh.soa.studentservice;

import com.sun.xml.internal.ws.client.ClientTransportException;
import pl.edu.agh.soa.studentservice.consumer.StudentService;
import pl.edu.agh.soa.studentservice.consumer.StudentServiceService;
import pl.edu.agh.soa.studentservice.helloconsumer.HelloWorld;
import pl.edu.agh.soa.studentservice.helloconsumer.HelloWorldService;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n\nHello world service");
        HelloWorldService helloWorldService = new HelloWorldService();
        HelloWorld hello = helloWorldService.getHelloWorldPort();


        System.out.println("Hello response for user Foo");
        System.out.println(hello.hello("Foo"));

        System.out.println("\n\nStudent service");
        StudentServiceService studentService = new StudentServiceService();
        StudentService student = studentService.getStudentServicePort();

        System.out.println("\n\nAttempt to access student service without authentication");
        try {
            System.out.println("Student list");
            System.out.println(student.getStudents());

            System.out.println("Filtered student list with phrase 'anna'");
            System.out.println(student.getFilteredStudents("anna"));
            System.out.println();

            System.out.println("Student with id 23452111");
            System.out.println(student.getStudentById("23452111"));

            System.out.println("Avatar for student with id 23124532111");
            System.out.println(student.getAvatarById("23124532111"));
        } catch (ClientTransportException ex) {
            System.out.println("Server response:");
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\nAttempt to access student service with authentication");
        setCredentials((BindingProvider)student);
        System.out.println("Student list");
        System.out.println(student.getStudents().getStudent());

        System.out.println("Filtered student list with phrase 'anna'");
        System.out.println(student.getFilteredStudents("anna"));

        System.out.println("Student with id 23452111");
        System.out.println(student.getStudentById("23452111"));

        System.out.println("Avatar for student with id 23124532111");
        System.out.println(student.getAvatarById("23124532111"));
    }

    private static void setCredentials(BindingProvider port) {
        BindingProvider prov = (BindingProvider)port;
        prov.getRequestContext().put(BindingProvider. USERNAME_PROPERTY, "user");
        prov.getRequestContext().put(BindingProvider. PASSWORD_PROPERTY, "user");
    }


}
