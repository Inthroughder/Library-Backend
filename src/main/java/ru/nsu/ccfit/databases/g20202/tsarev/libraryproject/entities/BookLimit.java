package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.bookplaces.Bookplace;

@Entity
@Data
@RequiredArgsConstructor
public class BookLimit {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue (strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genreId;

    @ManyToOne
    @JoinColumn(name = "bookplaceId")
    private Bookplace bookplaceId;

    private Long count;

}
