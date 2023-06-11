package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.LendDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.ReaderDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<ReaderDTO> getAllReaders(@RequestParam Map<String, String> params){

        String category = params.get("category");
        List<ReaderDTO> allReaders = new ArrayList<>();
        if (category.isEmpty()) {
            allReaders.addAll(readerService.getAllStudents(params));
            allReaders.addAll(readerService.getAllTeachers(params));
        } else {
            switch (category){
                case "Student":
                    allReaders.addAll(readerService.getAllStudents(params));
                    break;
                case "Teacher":
                    allReaders.addAll(readerService.getAllTeachers(params));
                    break;
            }
        }
        return allReaders;
    }

    @GetMapping("/debtor")
    @PreAuthorize("hasAuthority('USER')")
    public List<ReaderDTO> getAllDebtors(@RequestParam Map<String, String> params){

        String category = params.get("category");
        List<ReaderDTO> allReaders = new ArrayList<>();
        if (category.isEmpty()) {
            allReaders.addAll(readerService.getAllStudentsDebtors(params));
            allReaders.addAll(readerService.getAllTeachersDebtors(params));
        } else {
            switch (category){
                case "Student":
                    allReaders.addAll(readerService.getAllStudentsDebtors(params));
                    break;
                case "Teacher":
                    allReaders.addAll(readerService.getAllTeachersDebtors(params));
                    break;
            }
        }
        return allReaders;

    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ReaderDTO saveReader(@RequestBody ReaderDTO readerDTO){

        if (readerDTO.getCategory().equalsIgnoreCase("student")) {
            return readerService.saveStudent(readerDTO);
        } else if (readerDTO.getCategory().equalsIgnoreCase("teacher")) {
            return readerService.saveTeacher(readerDTO);
        } else {
            throw new RuntimeException("no such category");
        }

    }

    @PostMapping("/lend")
    @PreAuthorize("hasAuthority('ADMIN')")
    public LendDTO lendBook(@RequestBody LendDTO lendDTO){

        return readerService.lendBook(lendDTO);

    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long deleteReader(@RequestParam Long id){

        return readerService.delete(id);

    }

}
