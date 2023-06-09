package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.BookStatus;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Genre;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class BookDTO {

    private Long id;
    protected String name;
    private Long volume;
    private String genre;
    private Long price;
    private Long bookPlaceId;
    //private BookStatus status;
    private Date acceptedDate;

    public static BookDTO fromBookEntity(Book book){ //static to call in DatabaseService
        return BookDTO.builder()
                .name(book.getName())
                .volume(book.getVolume())
                //.genre(book.getGenreId().getName()) //todo
                .genre("genre sample")
                .price(book.getPrice())
                .bookPlaceId(book.getBookPlaceId())
                .acceptedDate(book.getAcceptedDate())
                .id(book.getId())
                .build();
    }

    public static Book toBookEntity(BookDTO bookDTO, Genre genre){
        return Book.builder()
                .name(bookDTO.getName())
                .volume(bookDTO.getVolume())
                .genreId(genre)
                .price(bookDTO.getPrice())
                .bookPlaceId(bookDTO.getBookPlaceId())
                .acceptedDate(bookDTO.getAcceptedDate())
                .build();
    }

}
