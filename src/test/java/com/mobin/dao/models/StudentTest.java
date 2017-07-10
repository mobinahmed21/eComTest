package com.mobin.dao.models;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobin.dao.services.StudentService;
import com.mobin.dto.mapper.DataMapper;
import com.mobin.dto.models.StudentDto;

public class StudentTest extends BaseTest{

	@Autowired
	StudentService studentService;
	DataMapper dataMapper;
	
	@Before
	public void before(){
		Mapper mapper =  new DozerBeanMapper();
		dataMapper = new DataMapper(mapper, null);
	}
	
	@Test
	public void getStudent(){
		Student student = studentService.fetchStudent(2);
		System.out.println(student.getName());
		System.out.println(student.getStandard());
		StudentDto dto = dataMapper.convertFromEntityToDto(student, StudentDto.class);
		System.out.println("ID:: "+dto.getId());
		System.out.println("NAME:: "+dto.getName());
		System.out.println("STANDARD:: "+dto.getStandard());
	}
}
