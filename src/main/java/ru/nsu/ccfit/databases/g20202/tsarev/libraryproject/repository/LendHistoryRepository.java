package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;

@Repository
public interface LendHistoryRepository extends JpaRepository<LendHistory, Long> {

    LendHistory save(LendHistory lendHistory);

    //@Query("delete from LendHistory lh where lh.reader = ?1")
    void deleteLendHistoriesByReader(Reader reader);

}
