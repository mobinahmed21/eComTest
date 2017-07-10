package com.mobin.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mobin.dao.models.CatalogProduct;

public interface CatalogProductRepository extends JpaRepository<CatalogProduct, Integer>{
	
	@Query(value = "SELECT pr FROM CatalogProduct AS pr WHERE pr.catalogId IN :catalogIds")
	List<CatalogProduct> getCatalogProductsOnCatalogIds(@Param("catalogIds") List<Integer> catalogIds);
	

}
