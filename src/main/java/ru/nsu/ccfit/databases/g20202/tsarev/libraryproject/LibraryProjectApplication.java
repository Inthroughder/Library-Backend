package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("ru.nsu.ccfit.databases.g20202.tsarev.libraryproject")
//@Configuration
@SpringBootApplication(scanBasePackages="ru.nsu.ccfit.databases.g20202.tsarev.libraryproject")
@EnableJpaRepositories("ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.repository")
@EntityScan("ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.entities")
public class LibraryProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryProjectApplication.class, args);

    }

}
