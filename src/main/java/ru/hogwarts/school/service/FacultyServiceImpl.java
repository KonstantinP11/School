package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyAlreadyExistsException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Факультет с " + id + " не найден в хранилище"));
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        getFaculty(faculty.getId());
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty removeFaculty(long id) {
        Faculty faculty = getFaculty(id);
        facultyRepository.delete(faculty);
        return faculty;
    }

    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
}