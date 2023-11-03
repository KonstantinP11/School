package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {
    private StudentServiceImpl underTest = new StudentServiceImpl();
    private Student student = new Student(1L, "Harry", 10);

    @Test
    void addStudent_shouldAddStudentAndReturnStudent() {
        Student result = underTest.addStudent(student);
        assertEquals(student, result);
    }

    @Test
    void getStudent_shouldReturnStudent() {
        underTest.addStudent(student);
        Optional<Student> result = underTest.getStudent(1L);
        assertEquals(student, result);
    }

    @Test
    void updateStudent_shouldReturnUpdatedStudent() {
        underTest.addStudent(student);
        Student student1 = new Student(2L, "Ron", 10);
        underTest.addStudent(student1);
        Student result = underTest.updateStudent(student1);
        assertEquals(student1, result);
    }

    @Test
    void removeStudent_shouldReturnDeletedStudent() {
        underTest.addStudent(student);
        Student result = underTest.removeStudent(1L);
        assertEquals(student,result);
    }

    @Test
    void getStudentByAge_shouldReturnStudentCollection() {
        Student student1 = new Student(2L, "Ron", 10);
        underTest.addStudent(student);
        underTest.addStudent(student1);
        Collection<Student> result = underTest.getStudentByAge(10);
        assertEquals(List.of(student, student1), result);
    }
}