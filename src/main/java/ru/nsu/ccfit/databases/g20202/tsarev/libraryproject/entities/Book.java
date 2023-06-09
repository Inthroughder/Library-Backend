package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @OneToMany(mappedBy = "book")
    private Set<LendHistory> lendHistories;

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
