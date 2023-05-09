package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.TeacherDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.ReaderService;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("/student/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return readerService.getStudentById(id);
    }

    @PostMapping("/student")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO){
        return readerService.saveStudent(studentDTO);
    }

    @GetMapping("/teacher/{id}")
    public TeacherDTO getTeacherById(@PathVariable Long id){
        return readerService.getTeacherById(id);
    }

    @PostMapping("/teacher")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO){
        return readerService.saveTeacher(teacherDTO);
    }

}
