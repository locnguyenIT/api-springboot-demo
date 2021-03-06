package com.example.apispringbootdemo.student;

import com.example.apispringbootdemo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Data Access Layer
public interface StudentRepository extends JpaRepository<Student, Integer> { //Spring Data JPA

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);


}
