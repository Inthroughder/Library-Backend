package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;

import java.sql.Date;

@Entity //i.e. it's a table
@Inheritance(strategy = InheritanceType.JOINED) //students and teachers extend readers; readers have common fields, students and teachers have unique
@SuperBuilder //required in every class in hierarchy
@RequiredArgsConstructor //should have no-args constructor
@AllArgsConstructor // TODO ???
@Data //lombok thing, creates getters and setters, and also toString, hash and equals methods
public class Reader {
    @jakarta.persistence.Id //field is an ID
    @GeneratedValue (strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    protected String fullName;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    protected Category categoryId;

    protected String department;

    protected String faculty;

    protected Date punishedUntil;

    protected Long punishAmount;

    protected Date dateLeft;

    protected Date dateJoined;

}
