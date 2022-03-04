package com.example.apispringbootdemo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //setup Mock StudentRepository class;
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void getStudent() {
        //when
        underTest.getStudent();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void getStudentById() {
    }

    @Test
    void canAddStudent() {
        //given
        String email = "loc@gmail.com";
        Student student = new Student("loc",
                email,
                LocalDate.now());
        //when
        underTest.addStudent(student);
        //then
        //Check student was given is right when studentRepo.save(). Compare student was given == student when studentRepo.save()
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);
    }

    @Test
    void willThrownExceptionWhenEmailStudentWasTaken() {
        //given
        String email = "loc@gmail.com";
        Student student = new Student("loc",
                email,
                LocalDate.now());

        given(studentRepository.findStudentByEmail(email)).willReturn(Optional.of(student));
        //when
        //then
        assertThatThrownBy(() -> underTest.addStudent(student))
                                .isInstanceOf(IllegalStateException.class)
                                .hasMessageContaining("Email already exist in database");

        verify(studentRepository,never()).save(any()); //studentRepository.save will never execute
    }

    @Test
    @Disabled
    void deleteStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}