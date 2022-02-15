package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository) {
        return args ->{
           Student mariam =
                    new Student(
                            1L,
                            "Mariam",
                            "Mariam.Jamal@gmail.com",
                            LocalDate.of(2000, Month.APRIL,5));


            Student aria =
                    new Student(
                            2L,
                            "Aria",
                            "Aria.Yazdi@gmail.com",
                            LocalDate.of(2005, Month.JUNE,21));

            repository.saveAll(
                    List.of(mariam,aria));
        };
    }

}
