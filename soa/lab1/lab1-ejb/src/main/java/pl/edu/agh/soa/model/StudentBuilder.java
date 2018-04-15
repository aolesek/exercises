package pl.edu.agh.soa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public final class StudentBuilder {
    private String firstName;
    private String lastName;
    private String studentId;
    private List<String> courses;
    private byte[] ava;

    private StudentBuilder() {
    }

    public static StudentBuilder aStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentBuilder courses(List<String> courses) {
        this.courses = courses;
        return this;
    }

    public StudentBuilder avatar(String  avaPathname) {
        if (!avaPathname.equals("")) {
            byte[] Photo;
            try {
                File f = new File("c:/avatar1.png");
                InputStream objFileIn = new FileInputStream(f);
                int length = (int) f.length();

                Photo = new byte[length];
                objFileIn.read(Photo);

                objFileIn.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            this.ava = Photo;
        }

        return this;
    }

    public Student build() {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentId(studentId);
        student.setCourses(courses);
        student.setAva(ava);
        return student;
    }
}
