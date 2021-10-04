package com.example.apispringbootdemo.config;

import com.example.apispringbootdemo.repository.StudentRepository;
import com.example.apispringbootdemo.student.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration //Config & set 2 student in database
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args ->  {
            Student loc = new Student(
                    "Nguyen Thanh Loc",
                    "ntloc.developer@gmail.com",
                    LocalDate.of(1999, Month.SEPTEMBER, 28));

            Student linh = new Student(
                    "Tran Ha Linh",
                    "halinh@gmail.com",
                    LocalDate.of(2002, Month.DECEMBER, 12));

            repository.saveAll(List.of(loc,linh));
        };
    }
}
