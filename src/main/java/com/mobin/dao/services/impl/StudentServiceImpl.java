package com.mobin.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobin.dao.models.Student;
import com.mobin.dao.repositories.StudentRepository;
import com.mobin.dao.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		// TODO Auto-generated constructor stub
		this.studentRepository = studentRepository;
	}
	
	@Override
	@Transactional
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
	}

	@Override
	public Student fetchStudent(Integer id) {
		// TODO Auto-generated method stub
		return this.studentRepository.findOne(id);
	}

}
