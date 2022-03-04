package com.example.apispringbootdemo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //spin up h2 database for testing
class StudentRepositoryTest {


    @Autowired
    private StudentRepository underTest;

    @AfterEach // After each test, delete all data -> clean state
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }


    @Test
    void itShouldCheckWhenStudentIsNotEmpty() {
        //Given
        String email = "loc@gmail.com";
        Student student = new Student("loc",
                                    email,
                                    LocalDate.now());
        underTest.save(student);
        //When
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);

        //Then
        assertThat(studentByEmail).isNotEmpty();

    }
    @Test
    void itShouldCheckWhenStudentIsEmpty() {
        //Given
        String email = "loc@gmail.com";
        //When
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);

        //Then
        assertThat(studentByEmail).isEmpty();


    }
}