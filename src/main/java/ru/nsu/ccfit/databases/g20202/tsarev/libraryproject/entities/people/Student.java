package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@RequiredArgsConstructor
@PrimaryKeyJoinColumn(name="id")
public class Student extends Reader {

    private String groupNumber;

}
