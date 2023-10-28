package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

@Service
public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    Faculty updateFaculty(Faculty faculty);

    Faculty removeFaculty(long id);
}
