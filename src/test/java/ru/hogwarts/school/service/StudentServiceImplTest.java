package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepository repository;
    @InjectMocks
     StudentServiceImpl underTest;
    private Student student = new Student(1L, "Harry", 10);

    @Test
    void addStudent_shouldAddStudentAndReturnStudent() {
        when(repository.save(student)).thenReturn(student);
        Student result = underTest.addStudent(student);
        assertEquals(student, result);
    }

    @Test
    void getStudent_shouldReturnStudent() {
        when(repository.findById(student.getId()))
                .thenReturn(Optional.of(student));
        Student result = underTest.getStudent(student.getId());
        assertEquals(student, result);
    }
    @Test
    void getStudent_shouldThrowExceptionWhenStudentNotFound() {
        when(repository.findById(student.getId()))
                .thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,
                () -> underTest.getStudent(student.getId()));
    }
    @Test
    void updateStudent_shouldReturnUpdatedStudent() {
        Student student1 = new Student(2L, "Ron", 10);
        when(repository.save(student1)).thenReturn(student1);
        Student result = underTest.addStudent(student1);
        assertEquals(student1, result);
    }

    @Test
    void removeStudent_shouldReturnDeletedStudent() {
        when(repository.findById(student.getId())).thenReturn(Optional.of(student));
        Student result = underTest.removeStudent(1L);
        assertEquals(student,result);
    }

    @Test
    void getStudentByAge_shouldReturnStudentCollection() {
        Student student1 =new Student(2L, "Ron", 10);
        when(repository.findAllByAge(student.getAge()))
                .thenReturn(List.of(student, student1));
        Collection<Student> result = underTest.getStudentByAge(10);
        assertEquals(List.of(student, student1), result);
    }
}