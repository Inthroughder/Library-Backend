package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.DTO.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.People.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository.StudentRepository;

@Service
public class DatabaseService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO save(StudentDTO studentDTO){
        Student student = studentRepository.save(StudentDTO.toEntity(studentDTO));
        return StudentDTO.fromEntity(student);
    }

}
