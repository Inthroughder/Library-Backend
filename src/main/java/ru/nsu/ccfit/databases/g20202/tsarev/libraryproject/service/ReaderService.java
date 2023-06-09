package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
public class ReaderService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPlaceRepository bookPlaceRepository;

    @Autowired
    private LendHistoryRepository lendHistoryRepository;

    public ReaderDTO getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.get();
        if (student != null) {
            return ReaderDTO.fromStudentEntity(student);
        } else {
            //TODO обработка если студента нет
            throw new RuntimeException("studenta net, ushel v zapoi");
        }
    }

    @Transactional(readOnly = true)
    public List<ReaderDTO> getAllStudents(Map<String, String> params){
        List<Specification<Student>> predicates = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "bookPlace" -> { //todo where is if
                    List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(entry.getValue());
                    predicates.add(StudentSpecifications.hasBookPlaces(bookPlaceIds));
                }
                case "faculty" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(StudentSpecifications.hasFaculty(entry.getValue()));
                }
                case "groupNumber" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(StudentSpecifications.hasGroupNumber(entry.getValue()));
                }
                //todo case year
            }
        }

        Specification<Student> result = Specification.allOf(predicates);
        List<Student> students = studentRepository.findAll(result);

        return students.stream().map(student -> ReaderDTO.fromStudentEntity(student)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReaderDTO> getAllTeachers(Map<String, String> params){
        List<Specification<Teacher>> predicates = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "bookPlace" -> { //"if" is inside hasBookplaces()
                    List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(entry.getValue());
                    predicates.add(TeacherSpecifications.hasBookPlaces(bookPlaceIds));
                }
                case "faculty" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(TeacherSpecifications.hasFaculty(entry.getValue()));
                }
                case "chair" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(TeacherSpecifications.hasChair(entry.getValue()));
                }
            }
        }

        Specification<Teacher> result = Specification.allOf(predicates);
        List<Teacher> teachers = teacherRepository.findAll(result);

        return teachers.stream().map(teacher -> ReaderDTO.fromTeacherEntity(teacher)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReaderDTO> getAllStudentsDebtors(Map<String, String> params){
        List<Specification<Student>> predicates = new ArrayList<>();

        long days = Long.valueOf(params.get("days"));

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "bookPlace" -> { //"if" is inside hasDebts()
                    List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(entry.getValue());
                    predicates.add(StudentSpecifications.hasDebts(bookPlaceIds, days));
                }
                case "faculty" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(StudentSpecifications.hasFaculty(entry.getValue()));
                }
                case "groupNumber" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(StudentSpecifications.hasGroupNumber(entry.getValue()));
                }
                //todo case year
            }
        }

        Specification<Student> result = Specification.allOf(predicates);
        List<Student> students = studentRepository.findAll(result);

        return students.stream().map(student -> ReaderDTO.fromStudentEntity(student)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReaderDTO> getAllTeachersDebtors(Map<String, String> params){
        List<Specification<Teacher>> predicates = new ArrayList<>();

        long days = Long.valueOf(params.get("days"));

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "bookPlace" -> { //"if" is inside hasDebts()
                    List<Long> bookPlaceIds = bookPlaceRepository.findAllByName(entry.getValue());
                    predicates.add(TeacherSpecifications.hasDebts(bookPlaceIds, days));
                }
                case "faculty" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(TeacherSpecifications.hasFaculty(entry.getValue()));
                }
                case "chair" -> {
                    if (!entry.getValue().isEmpty())
                        predicates.add(TeacherSpecifications.hasChair(entry.getValue()));
                }
            }
        }

        Specification<Teacher> result = Specification.allOf(predicates);
        List<Teacher> teachers = teacherRepository.findAll(result);

        return teachers.stream().map(teacher -> ReaderDTO.fromTeacherEntity(teacher)).collect(Collectors.toList());
    }

    public ReaderDTO saveStudent(ReaderDTO studentDTO){
        Category category = categoryRepository.getByCategoryName(studentDTO.getCategory());
        Student entity = ReaderDTO.toStudentEntity(studentDTO, category);
        Student student = studentRepository.save(entity);
        TicketHistory ticketHistory = toEntity(studentDTO.getTicketId(), student);
        ticketHistoryRepository.save(ticketHistory);
        return ReaderDTO.fromStudentEntity(student);
    }

    @Transactional
    public ReaderDTO saveTeacher(ReaderDTO teacherDTO){

        Category category = categoryRepository.getByCategoryName(teacherDTO.getCategory());
        Teacher entity = ReaderDTO.toTeacherEntity(teacherDTO, category);
        Teacher teacher = teacherRepository.save(entity);
        TicketHistory ticketHistory = toEntity(teacherDTO.getTicketId(), teacher);
        ticketHistoryRepository.save(ticketHistory);
        return ReaderDTO.fromTeacherEntity(teacher);

    }

    private TicketHistory toEntity(Long ticketId, Reader reader){ //для автоматического сохранения в историю читательских билетов
        Date dateNow = Date.valueOf(LocalDate.now());
        return TicketHistory.builder()
                .readerId(reader)
                .ticketId(ticketId)
                .validFromDate(dateNow)
                .validUntilDate(reader.getTicketValidUntil())
                .build();
    }

    @Transactional
    public LendDTO lendBook(LendDTO lendDTO){

        Long bookPlaceId = bookPlaceRepository.findAllByName(lendDTO.getBookPlaceName()).get(0);

        Book book = bookRepository.getDistinctByBookPlaceId(bookPlaceId).get(0);

        Reader reader = ticketHistoryRepository.getReaderByTicketId(lendDTO.getTicketId(), Date.valueOf(LocalDate.now()));

        LendHistory lendHistory = LendDTO.toLendHistoryEntity(reader, book);

        lendHistoryRepository.save(lendHistory);

        return LendDTO.fromLendHistoryEntity(lendHistory, lendDTO.getBookPlaceName(), lendDTO.getBookName(), lendDTO.getTicketId());

    }

    @Transactional
    public Long delete(Long readerId){//cascade: lend history, ticket history, reader

        Optional<Student> student = studentRepository.findById(readerId);
        Reader reader = null;

        if (student.isEmpty()){
            Optional<Teacher> teacher = teacherRepository.findById(readerId);
            if (teacher.isPresent()){
                reader = teacher.get();
            }
        } else {
            reader = student.get();
        }

        if (reader != null) {

            lendHistoryRepository.deleteLendHistoriesByReader(reader);

            ticketHistoryRepository.deleteTicketHistoriesByReaderId(reader);

            studentRepository.deleteById(readerId);

            teacherRepository.deleteById(readerId);

        }

        return readerId;

    }

}
