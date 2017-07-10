package com.mobin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobin.dao.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	
}
