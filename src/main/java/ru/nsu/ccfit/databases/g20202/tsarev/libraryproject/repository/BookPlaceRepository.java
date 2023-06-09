package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.bookplaces.BookPlace;

import java.util.List;

@Repository
public interface BookPlaceRepository extends JpaRepository<BookPlace, Long> {

    @Query("select b.id from BookPlace b where b.name like %?1%")
    public List<Long> findAllByName(String name);

    @Query("select b.id from BookPlace b where b.name like %?1%")
    public List<BookPlace> findAllEntitiesByName(String name);

}
