package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.LendHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;

import java.util.List;
import java.util.Objects;

public class StudentSpecifications {

    public static Specification<Student> hasDepartment(String department) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("department"), "%" + department + "%");
    }

    public static Specification<Student> hasFaculty(String faculty) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<String>get("faculty"), faculty);
    }

    public static Specification<Student> hasGroupNumber(String groupNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<String>get("groupNumber"), groupNumber);
    }

    public static Specification<Student> hasBookPlaces(List<Long> bookPlaceIds) {
        return (root, query, criteriaBuilder) -> {
            Join<LendHistory, Student> history = root.join("lendHistories");
            Join<Book, LendHistory> books = history.join("book");

            if (Objects.nonNull(bookPlaceIds) && !bookPlaceIds.isEmpty()) {
                return criteriaBuilder.in(books.get("bookPlaceId")).value(bookPlaceIds);
            }

            return criteriaBuilder.isNotNull(books.get("bookPlaceId"));
        };
    }

}
