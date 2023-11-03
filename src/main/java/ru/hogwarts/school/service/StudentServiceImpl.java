package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@Service
public class StudentServiceImpl implements StudentService {

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Студент с " + id + " не найден в хранилище"));
    }

    @Override
    public Student updateStudent(Student student) {
        getStudent(student.getId());
        return studentRepository.save(student);
    }

    @Override
    public Student removeStudent(long id) {
        Student student = getStudent(id);
        studentRepository.delete(student);
        return student;
    }

    @Override
    public Collection<Student> getStudentByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
}