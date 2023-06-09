package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.PunishHistory;

@Repository
public interface PunishHistoryRepository extends JpaRepository<PunishHistory, Long> {

}
