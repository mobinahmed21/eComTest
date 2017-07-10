package com.mobin.dao.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobin.dao.models.Catalog;
import com.mobin.dao.repositories.CatalogRepository;
import com.mobin.dao.services.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

	CatalogRepository catalogRepository;
	
	public CatalogServiceImpl(CatalogRepository catalogRepository) {
		// TODO Auto-generated constructor stub
		this.catalogRepository = catalogRepository;
	}
	
	@Override
	public List<Catalog> getCatalogList() {
		// TODO Auto-generated method stub
		return catalogRepository.getAllCatologs();
	}

}
