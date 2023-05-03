package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.People.Student;

@Repository
public interface ReaderRepository extends JpaRepository<Student, Long> {

    Student save(Student student);

}
