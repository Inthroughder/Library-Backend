package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.DTO.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.People.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository.ReaderRepository;

@Service
public class DatabaseService {

    @Autowired
    private ReaderRepository readerRepository;

    public StudentDTO saveStudent(StudentDTO studentDTO){
        Student student = readerRepository.save(StudentDTO.toEntity(studentDTO));
        return StudentDTO.fromEntity(student);
    }

}
