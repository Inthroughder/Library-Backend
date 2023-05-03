package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan("ru.nsu.ccfit.databases.g20202.tsarev.libraryproject")
//@Configuration
@SpringBootApplication(scanBasePackages="ru.nsu.ccfit.databases.g20202.tsarev.libraryproject")
public class LibraryProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryProjectApplication.class, args);

    }

}
