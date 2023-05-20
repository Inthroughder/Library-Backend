package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LimitId implements Serializable {

    private Long genreId;

    private Long bookPlaceId;

}
