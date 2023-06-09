package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class ReaderDTO {

    private Long id;
    protected String fullName;
    protected String category;
    protected String faculty;
    protected Long ticketId;
    protected Date ticketValidUntil;
    protected Date punishedUntil;
    protected Long punishAmount;
    protected Date dateLeft;
    protected Date dateJoined;
    private String groupNumber;
    private String chair;

    public static ReaderDTO fromStudentEntity(Student student){ //static to call in DatabaseService
        return ReaderDTO.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .category(student.getCategoryId().getCategoryName())
                .faculty(student.getFaculty())
                .ticketId(student.getTicketId())
                .ticketValidUntil(student.getTicketValidUntil())
                .punishedUntil(student.getPunishedUntil())
                .punishAmount(student.getPunishAmount())
                .dateLeft(student.getDateLeft())
                .dateJoined(student.getDateJoined())
                .groupNumber(student.getGroupNumber())
                .build();
    }

    public static Student toStudentEntity(ReaderDTO studentDTO, Category category){
        return Student.builder()
                .id(studentDTO.getId())
                .fullName(studentDTO.getFullName())
                .categoryId(category)
                .faculty(studentDTO.getFaculty())
                .ticketId(studentDTO.getTicketId())
                .ticketValidUntil(Date.valueOf(LocalDate.now().plusMonths(12)))
                .punishedUntil(studentDTO.getPunishedUntil())
                .punishAmount(studentDTO.getPunishAmount())
                .dateLeft(studentDTO.getDateLeft())
                .dateJoined(studentDTO.getDateJoined())
                .groupNumber(studentDTO.getGroupNumber())
                .build();
    }

    public static ReaderDTO fromTeacherEntity(Teacher teacher){ //static to call in DatabaseService
        return ReaderDTO.builder()
                .id(teacher.getId())
                .fullName(teacher.getFullName())
                .category(teacher.getCategoryId().getCategoryName())
                .faculty(teacher.getFaculty())
                .ticketId(teacher.getTicketId())
                .ticketValidUntil(teacher.getTicketValidUntil())
                .punishedUntil(teacher.getPunishedUntil())
                .punishAmount(teacher.getPunishAmount())
                .dateLeft(teacher.getDateLeft())
                .dateJoined(teacher.getDateJoined())
                .chair(teacher.getChair())
                .build();
    }

    public static Teacher toTeacherEntity(ReaderDTO teacherDTO, Category category){
        return Teacher.builder()
                .id(teacherDTO.getId())
                .fullName(teacherDTO.getFullName())
                .categoryId(category)
                .faculty(teacherDTO.getFaculty())
                .ticketId(teacherDTO.getTicketId())
                .ticketValidUntil(Date.valueOf(LocalDate.now().plusMonths(12)))
                .punishedUntil(teacherDTO.getPunishedUntil())
                .punishAmount(teacherDTO.getPunishAmount())
                .dateLeft(teacherDTO.getDateLeft())
                .dateJoined(teacherDTO.getDateJoined())
                .chair(teacherDTO.getChair())
                .build();
    }

}
