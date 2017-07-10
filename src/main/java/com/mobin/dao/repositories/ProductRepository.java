package com.mobin.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mobin.dao.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query(value = "SELECT pr FROM Product AS pr WHERE pr.id IN :productIds")
	List<Product> getProductsFromIds(@Param("productIds") List<Integer> productIds);
}
