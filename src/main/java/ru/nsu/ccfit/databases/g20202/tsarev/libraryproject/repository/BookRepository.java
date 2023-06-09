package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getDistinctByBookPlaceId(Long bookPlaceId);

    //@Query("select distinct s from Student s join s.lendHistories h join h.book b where b.bookPlaceId in :bookPlaceIds")

    @Query("select b, count(b) as cnt from Book b join b.lendHistories h join h.reader r where b.bookPlaceId = :bookPlaceId group by b order by cnt desc")//bookplace
    List<Book> findByBookplace(Long bookPlaceId);

    @Query("select b, count(b) as cnt from Book b join b.lendHistories h join h.reader r where r.faculty = :faculty group by b order by cnt desc")//faculty
    List<Book> findByFaculty(String faculty);

    @Query("select b, count(b) as cnt from Book b join b.lendHistories h join h.reader r where b.bookPlaceId = :bookPlaceId and r.faculty = :faculty group by b order by cnt desc")//bookplace and faculty
    List<Book> findByFacultyAndBookplace(Long bookPlaceId, String faculty);

    @Query("select b, count(b) as cnt from Book b join b.lendHistories h join h.reader r group by b order by cnt desc")//all
    List<Book> findAll();

    @Query("select b from Book b join b.lendHistories h where b.status = 4 and b.acceptedDate between ?1 and ?2")
    List<Book> findAllLost(Date yearStart, Date yearEnd);

    @Query("select b from Book b where b.bookPlaceId in :bookPlaceIds and b.name like %:bookName%")
    List<Book> findTotalByBookPlaceIds(List<Long> bookPlaceIds, String bookName);

    @Query("select b from Book b where b.name like %:bookName%")
    List<Book> findTotal(String bookName);

}
