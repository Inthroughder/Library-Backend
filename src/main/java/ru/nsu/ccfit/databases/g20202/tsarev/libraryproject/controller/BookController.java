package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.BookDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.LendDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.ReaderDTO;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.BookService;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.service.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookDTO> getTopBooks(@RequestParam Map<String, String> params){

        return bookService.getTopBooks(params);

    }

    @GetMapping("/lost")
    public List<BookDTO> getLostBooks(@RequestParam Map<String, String> params){

        return bookService.getLostBooks(params);

    }

    @GetMapping("/total")
    public List<BookDTO> getTotalBooks(@RequestParam Map<String, String> params){

        return bookService.getTotalBooks(params);

    }

}
