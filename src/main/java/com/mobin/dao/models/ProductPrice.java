package com.mobin.dao.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product_price")
public class ProductPrice extends BaseDao {

	private Integer productId;
	private Integer price;
}
