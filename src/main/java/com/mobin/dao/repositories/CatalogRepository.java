package com.mobin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobin.dao.models.Catalog;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Integer>{

	@Query(value = "SELECT ct FROM Catalog AS ct")
	List<Catalog> getAllCatologs();
}
