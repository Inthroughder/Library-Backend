package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.People.Student;

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
    protected Date left;
    protected Date joined;
    private String group;

    public static StudentDTO fromEntity(Student student){ //static to call in DatabaseService

        return StudentDTO.builder()
                .id(student.getId())
                .group(student.getGroup())
                .fullName(student.getFullName())
                .categoryId(student.getCategoryId())
                .department(student.getDepartment())
                .faculty(student.getFaculty())
                .ticketId(student.getTicketId())
                .ticketValidUntil(student.getTicketValidUntil())
                .punishedUntil(student.getPunishedUntil())
                .punishAmount(student.getPunishAmount())
                .left(student.getLeft())
                .joined(student.getJoined())
                .build();

    }

    public static Student toEntity(StudentDTO studentDTO){
        return Student.builder()
                .id(studentDTO.getId())
                .group(studentDTO.getGroup())
                .fullName(studentDTO.getFullName())
                .categoryId(studentDTO.getCategoryId())
                .department(studentDTO.getDepartment())
                .faculty(studentDTO.getFaculty())
                .ticketId(studentDTO.getTicketId())
                .ticketValidUntil(studentDTO.getTicketValidUntil())
                .punishedUntil(studentDTO.getPunishedUntil())
                .punishAmount(studentDTO.getPunishAmount())
                .left(studentDTO.getLeft())
                .joined(studentDTO.getJoined())
                .build();
    }

}
