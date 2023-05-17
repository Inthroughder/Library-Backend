package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.StudentDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.TeacherDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Book;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.Category;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.history.TicketHistory;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Reader;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Student;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities.people.Teacher;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    public StudentDTO saveStudent(StudentDTO studentDTO){
        Category category = categoryRepository.getReferenceById(studentDTO.getCategoryId());
        Student entity = StudentDTO.toEntity(studentDTO, category);
        Student student = studentRepository.save(entity);
        return StudentDTO.fromEntity(student);
    }

    public StudentDTO getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.get();
        if (student != null) {
            return StudentDTO.fromEntity(student);
        } else {
            //TODO обработка если студента нет
            throw new RuntimeException("studenta net, ushel v zapoi");
        }
    }

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> StudentDTO.fromEntity(student)).collect(Collectors.toList());
    }

    @Transactional
    public TeacherDTO saveTeacher(TeacherDTO teacherDTO){
        Category category = categoryRepository.getReferenceById(teacherDTO.getCategoryId());
        Teacher entity = TeacherDTO.toEntity(teacherDTO, category);
        Teacher teacher = teacherRepository.save(entity);
        //TODO сохранить тикет в истории, в тикете сохранить ссылку на ридера
        TicketHistory ticketHistory = toEntity(teacherDTO.getTicketId(), teacher);
        ticketHistoryRepository.save(ticketHistory);
        return TeacherDTO.fromEntity(teacher);
    }

    public TeacherDTO getTeacherById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        Teacher teacher = teacherOptional.get();
        if (teacher != null) {
            return TeacherDTO.fromEntity(teacher);
        } else {
            //TODO обработка если преподавателя нет
            throw new RuntimeException("prepoda net, ushel v zapoi");
        }
    }

    public List<Book> getBooksByBookPlaceId(Long id){
        return bookRepository.getDistinctByBookPlaceId(id);
    }

    private TicketHistory toEntity(Long ticketId, Reader reader){
        Date dateNow = Date.valueOf(LocalDate.now());
        return TicketHistory.builder()
                .readerId(reader)
                .ticketId(ticketId)
                .validFromDate(dateNow)
                .validUntilDate(dateNow)//TODO
                .build();
    }
}
