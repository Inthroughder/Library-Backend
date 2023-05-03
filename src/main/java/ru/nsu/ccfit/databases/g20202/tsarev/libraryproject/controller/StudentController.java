package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.DTO.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.DatabaseService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    DatabaseService databaseService;

    @PostMapping
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO){
        return databaseService.saveStudent(studentDTO);
    }

}
