package com.mobin.dao.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student extends BaseDao{

	private String name;
	private Integer standard;

}
