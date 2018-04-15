package pl.edu.agh.soa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    private List<String> courses;
    private byte[] ava;

    public Student() {

    }

    @XmlMimeType("application/octet-stream")
    public byte[] getAva() {
        return ava;
    }

    public void setAva(byte[] ava) {
        this.ava = ava;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String toString() {
        return firstName + " "
                + lastName + " "
                + studentId + " "
                + courses.toString();
    }

}
