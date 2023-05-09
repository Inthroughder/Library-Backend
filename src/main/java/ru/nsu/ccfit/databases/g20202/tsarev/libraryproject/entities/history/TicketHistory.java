package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;

import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
@Builder
public class TicketHistory {

    @jakarta.persistence.Id //field is an ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader readerId;

    private Long ticketId;

    private Date validFromDate;

    private Date validUntilDate;

}
