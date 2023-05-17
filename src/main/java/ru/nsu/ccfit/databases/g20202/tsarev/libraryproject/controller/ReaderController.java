package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.TeacherDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
//@CrossOrigin(origins = "*")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("/student/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return readerService.getStudentById(id);
    }

    @GetMapping("/student")
    public List<StudentDTO> getAllStudents(){
        return readerService.getAllStudents();
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
