package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;

import java.sql.Date;

@Builder
@AllArgsConstructor
@Data
public class StudentDTO {

    private Long id;
    protected String fullName;
    protected Long categoryId;
    protected String department;
    protected String faculty;
    protected Long ticketId;
    protected Date ticketValidUntil;
    protected Date punishedUntil;
    protected Long punishAmount;
    protected Date dateLeft;
    protected Date dateJoined;
    private String groupNumber;

    public static StudentDTO fromEntity(Student student){ //static to call in DatabaseService

        return StudentDTO.builder()
                .id(student.getId())
                .groupNumber(student.getGroupNumber())
                .fullName(student.getFullName())
                .categoryId(student.getCategoryId().getId())
                .department(student.getDepartment())
                .faculty(student.getFaculty())
                .punishedUntil(student.getPunishedUntil())
                .punishAmount(student.getPunishAmount())
                .dateLeft(student.getDateLeft())
                .dateJoined(student.getDateJoined())
                .build();

    }

    public static Student toEntity(StudentDTO studentDTO, Category category){
        return Student.builder()
                .id(studentDTO.getId())
                .groupNumber(studentDTO.getGroupNumber())
                .fullName(studentDTO.getFullName())
                .categoryId(category)
                .department(studentDTO.getDepartment())
                .faculty(studentDTO.getFaculty())
                .punishedUntil(studentDTO.getPunishedUntil())
                .punishAmount(studentDTO.getPunishAmount())
                .dateLeft(studentDTO.getDateLeft())
                .dateJoined(studentDTO.getDateJoined())
                .build();
    }

}
