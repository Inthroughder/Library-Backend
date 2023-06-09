package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeacherSpecifications {

    public static Specification<Teacher> hasFaculty(String faculty) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<String>get("faculty"), faculty);
    }

    public static Specification<Teacher> hasChair(String chair) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<String>get("chair"), chair);
    }

    public static Specification<Teacher> hasBookPlaces(List<Long> bookPlaceIds) {
        return (root, query, criteriaBuilder) -> {
            Join<LendHistory, Teacher> history = root.join("lendHistories");
            Join<Book, LendHistory> books = history.join("book");

            if (Objects.nonNull(bookPlaceIds) && !bookPlaceIds.isEmpty()) {
                return criteriaBuilder.in(books.get("bookPlaceId")).value(bookPlaceIds);
            }

            return criteriaBuilder.isNotNull(books.get("bookPlaceId"));
        };
    }

    public static Specification<Teacher> hasDebts(List<Long> bookPlaceIds, long days) {
        return (root, query, criteriaBuilder) -> {
            Join<LendHistory, Teacher> history = root.join("lendHistories");
            Join<Book, LendHistory> books = history.join("book");

            Date requiredDate = Date.valueOf(LocalDate.now().minusDays(days));

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.lessThan(history.get("mustReturnDate"), requiredDate));

            predicates.add(criteriaBuilder.isNull(history.get("returnedDate")));

            if (Objects.nonNull(bookPlaceIds) && !bookPlaceIds.isEmpty()) {
                predicates.add(criteriaBuilder.in(books.get("bookPlaceId")).value(bookPlaceIds));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
