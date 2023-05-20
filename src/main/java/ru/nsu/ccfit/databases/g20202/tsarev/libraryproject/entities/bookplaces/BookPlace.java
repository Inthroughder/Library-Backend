package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.bookplaces;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class BookPlace {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @Enumerated
    private BookPlaceType type;

    private String name;

}
