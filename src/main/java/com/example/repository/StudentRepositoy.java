package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;

public interface StudentRepositoy extends JpaRepository<Student, Integer> {

}
