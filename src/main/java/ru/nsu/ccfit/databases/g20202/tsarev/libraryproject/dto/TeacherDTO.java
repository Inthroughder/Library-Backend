package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;

import java.sql.Date;

@Builder
@AllArgsConstructor
@Data
public class TeacherDTO {

    private Long id;
    protected String fullName;
    protected String category;
    protected String department;
    protected String faculty;
    protected Long ticketId;
    protected Date ticketValidUntil;
    protected Date punishedUntil;
    protected Long punishAmount;
    protected Date dateLeft;
    protected Date dateJoined;
    private String chair;

    public static TeacherDTO fromEntity(Teacher teacher){ //static to call in DatabaseService

        return TeacherDTO.builder()
                .id(teacher.getId())
                .chair(teacher.getChair())
                .fullName(teacher.getFullName())
                .category(teacher.getCategoryId().getCategoryName())
                .department(teacher.getDepartment())
                .faculty(teacher.getFaculty())
                .punishedUntil(teacher.getPunishedUntil())
                .punishAmount(teacher.getPunishAmount())
                .dateLeft(teacher.getDateLeft())
                .dateJoined(teacher.getDateJoined())
                .build();

    }

    public static Teacher toEntity(TeacherDTO teacherDTO, Category category){
        return Teacher.builder()
                .id(teacherDTO.getId())
                .chair(teacherDTO.getChair())
                .fullName(teacherDTO.getFullName())
                .categoryId(category)
                .department(teacherDTO.getDepartment())
                .faculty(teacherDTO.getFaculty())
                .punishedUntil(teacherDTO.getPunishedUntil())
                .punishAmount(teacherDTO.getPunishAmount())
                .dateLeft(teacherDTO.getDateLeft())
                .dateJoined(teacherDTO.getDateJoined())
                .build();
    }

}