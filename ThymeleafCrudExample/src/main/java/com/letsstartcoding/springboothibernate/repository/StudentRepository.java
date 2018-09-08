package com.letsstartcoding.springboothibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsstartcoding.springboothibernate.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
