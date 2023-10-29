package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyAlreadyExistsException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        if (faculties.containsValue(faculty)) {
            throw new FacultyAlreadyExistsException("Студент " + faculty + " уже был в хранилище");
        }
        long id = ++idCounter;
        faculty.setId(id);
        return faculties.put(id, faculty);
    }

    @Override
    public Faculty getFaculty(long id) {

        Faculty faculty = faculties.get(id);
        if (faculty == null) {
            throw new FacultyNotFoundException("Факультет с " + id + " не найден в хранилище");
        }
        return faculty;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            throw new FacultyNotFoundException("Факультет с " + faculty.getId() + " не найден в хранилище");
        }
        return faculties.put(faculty.getId(), faculty);
    }

    @Override
    public Faculty removeFaculty(long id) {
        Faculty faculty = faculties.remove(id);
        if (faculty == null) {
            throw new FacultyNotFoundException("Факультет с " + id + " не найден в хранилище");
        }
        return faculty;
    }

    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toUnmodifiableList());
    }
}