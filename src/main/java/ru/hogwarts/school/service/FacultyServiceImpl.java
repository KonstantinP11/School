package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

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
                .orElseThrow(() -> new FacultyNotFoundException("Факультет с id " + id + " не найден в хранилище"));
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

    @Override
    public Collection<Faculty> findFacultyByNameIgnoreCase(String name) {
        return facultyRepository.findAllByNameIgnoreCase(name);
    }

    @Override
    public Collection<Faculty>  findFacultyByColorIgnoreCase(String color) {
        return facultyRepository.findAllByColorIgnoreCase(color);
    }
}