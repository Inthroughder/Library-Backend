package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.People;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@RequiredArgsConstructor
public class Student extends Reader {

    private String group;

}
