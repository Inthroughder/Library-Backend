package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.bookplaces.Bookplace;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LimitId implements Serializable {

    private Long genreId;

    private Long bookplaceId;

}
