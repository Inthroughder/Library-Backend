package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher save(Teacher teacher);

}
