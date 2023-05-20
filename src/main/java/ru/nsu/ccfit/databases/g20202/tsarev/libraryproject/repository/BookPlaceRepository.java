package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.bookplaces.Bookplace;

@Repository
public interface BookPlaceRepository extends JpaRepository<Bookplace, Long> {

    @Query("select b.id from Bookplace b where b.name = :name")
    public Long findByName(String name);

}
