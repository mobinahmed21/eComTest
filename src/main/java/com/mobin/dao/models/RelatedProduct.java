package com.mobin.dao.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "related_products")
@Setter
@Getter
public class RelatedProduct extends BaseDao {

	private Integer prodcutId;
	private Integer parentProductId;
}
