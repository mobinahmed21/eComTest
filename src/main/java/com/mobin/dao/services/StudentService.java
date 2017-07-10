package com.mobin.dao.services;

import com.mobin.dao.models.Student;

public interface StudentService {

	public void insertStudent(Student student);
	
	public Student fetchStudent(Integer id);
}
