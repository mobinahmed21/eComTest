package com.mobin.dao.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_products")
@Setter
@Getter
public class CatalogProduct extends BaseDao{

	private Integer catalogId;
	private Integer productId;
	private Integer quantity;
	private Boolean configurable;
}
