package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;

import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
public class FineHistory {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader readerId;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book bookId;

    @Enumerated
    private FineType fineType;

    private Long fineValue;

    private Date fineAssignedDate;

    private Date finePaidDate;

}
