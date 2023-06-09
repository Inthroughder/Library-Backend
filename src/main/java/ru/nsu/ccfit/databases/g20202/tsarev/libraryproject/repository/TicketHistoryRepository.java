package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.TicketHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;

import java.sql.Date;

@Repository
public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

    TicketHistory save(TicketHistory ticketHistory);

    @Query("select th.readerId from TicketHistory th where th.ticketId = ?1 and th.validUntilDate > ?2")
    Reader getReaderByTicketId(Long ticketId, Date validUntil);

    //@Query("delete from TicketHistory th where th.readerId = ?1")
    void deleteTicketHistoriesByReaderId(Reader reader);

}
