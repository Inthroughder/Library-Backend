package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.TeacherDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.ReaderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("/student")
    public List<StudentDTO> getAllStudents(@RequestParam(required = false) Map<String, String> params){
        return readerService.getAllStudents(params);
    }

    @PostMapping("/student")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO){
        return readerService.saveStudent(studentDTO);
    }

    @PostMapping("/teacher")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO){
        return readerService.saveTeacher(teacherDTO);
    }

}
