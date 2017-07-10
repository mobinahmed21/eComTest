package com.mobin.dao.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "catalogs")
public class Catalog extends BaseDao {

	private String name;
	private String description;
}
