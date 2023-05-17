package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
public class Book {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    private String name;

    private Long volume;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genreId;

    private Long price;

    private Long bookPlaceId;

    @Enumerated
    private BookStatus status;

    private Date acceptedDate;

}
