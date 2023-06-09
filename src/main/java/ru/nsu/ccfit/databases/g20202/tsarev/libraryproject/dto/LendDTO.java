package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class LendDTO {

    private Long id;
    private Long ticketId;
    private String bookPlaceName;
    private String bookName;

    public static LendDTO fromLendHistoryEntity(LendHistory lendHistory, String bookPlaceName, String bookName, Long ticketId){ //static to call in DatabaseService
        return LendDTO.builder() //todo
                .id(lendHistory.getId())
                .ticketId(ticketId)
                .bookPlaceName(bookPlaceName)
                .bookName(bookName)
                .build();
    }

    public static LendHistory toLendHistoryEntity(Reader reader, Book book){
        return LendHistory.builder()
                .reader(reader)
                .book(book)
                .mustReturnDate(Date.valueOf(LocalDate.now().plusMonths(1)))
                .takenDate(Date.valueOf(LocalDate.now()))
                .build();
    }

}
