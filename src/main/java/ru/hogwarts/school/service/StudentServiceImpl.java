package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentAlreadyExistsException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public Student addStudent(Student student) {
        if (students.containsValue(student)) {
            throw new StudentAlreadyExistsException("Студент " + student + " уже был в хранилище");
        }
        long id = ++idCounter;
        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(long id) {

        Student student = students.get(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент с " + id + " не найден в хранилище");
        }
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            throw new StudentNotFoundException("Студент с " + student.getId() + " не найден в хранилище");
        }
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student removeStudent(long id) {
        Student student = students.remove(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент с " + id + " не найден в хранилище");
        }
        return student;
    }

    @Override
    public Collection<Student> getStudentByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toUnmodifiableList());
    }

}