package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.BookDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.LendDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.ReaderDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.TicketHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPlaceRepository bookPlaceRepository;

    @Transactional(readOnly = true)
    public List<BookDTO> getTopBooks(Map<String, String> params){

        List<Book> books;

        String bookPlace = params.get("bookPlace").isEmpty() ? null : params.get("bookPlace");
        String faculty = params.get("faculty").isEmpty() ? null : params.get("faculty");

        if (Objects.nonNull(bookPlace) && Objects.nonNull(faculty)){

            List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(bookPlace);
            Long bookPlaceId = bookPlaceIds.get(0);
            books = bookRepository.findByFacultyAndBookplace(bookPlaceId, faculty);

        } else if (Objects.nonNull(faculty)) {

            books = bookRepository.findByFaculty(faculty);

        } else if (Objects.nonNull(bookPlace)){

            List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(bookPlace);
            Long bookPlaceId = bookPlaceIds.get(0);
            books = bookRepository.findByBookplace(bookPlaceId);

        } else {

            books = bookRepository.findAll();

        }

        return books.stream().map(BookDTO::fromBookEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getLostBooks(Map<String, String> params){

        String bookPlace = params.get("bookPlace").isEmpty() ? null : params.get("bookPlace");
        String priceLessThan = params.get("priceLessThan").isEmpty() ? null : params.get("priceLessThan");

        String year = params.get("year").isEmpty() ? String.valueOf(LocalDate.now().getYear()) : params.get("year");
        LocalDate firstDay = LocalDate.ofYearDay(Integer.valueOf(year), 1);
        LocalDate lastDay = firstDay.with(lastDayOfYear());

        List<Book> books = bookRepository.findAllLost(Date.valueOf(firstDay), Date.valueOf(lastDay));

        if (Objects.nonNull(bookPlace)){
            List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(bookPlace);
            books = books.stream()
                    .filter(b -> bookPlaceIds.contains(b.getBookPlaceId()))
                    .collect(Collectors.toList());
        }

        if (Objects.nonNull(priceLessThan)){

            books = books.stream()
                    .filter(b -> b.getPrice() <= Long.valueOf(priceLessThan))
                    .collect(Collectors.toList());
        }

        return books.stream().map(BookDTO::fromBookEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getTotalBooks(Map<String, String> params){

        String bookPlace = params.get("bookPlace").isEmpty() ? null : params.get("bookPlace");
        String bookName = params.get("bookName");
        List<Book> books;

        if (Objects.nonNull(bookPlace)){
            List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(bookPlace);
            books = bookRepository.findTotalByBookPlaceIds(bookPlaceIds, bookName);
        } else {
            books = bookRepository.findTotal(bookName);
        }

        return books.stream().map(BookDTO::fromBookEntity).collect(Collectors.toList());
    }

}
