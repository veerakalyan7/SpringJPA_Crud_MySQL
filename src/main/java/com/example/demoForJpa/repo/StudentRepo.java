package com.example.demoForJpa.repo;

import com.example.demoForJpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {



}
