package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
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
                .orElseThrow(() -> new StudentNotFoundException("Студент с id " + id + " не найден в хранилище"));
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

    @Override
    public Collection<Student> getAllStudentAgeBetweenMinAndMax(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Faculty studentFaculty(long studentId) {
        return getStudent(studentId).getFaculty();
    }

    @Override
    public Collection<Student> readByFacultyId(long facultyId) {
        return studentRepository.findAllByFaculty_id(facultyId);
    }
}