package com.mobin.dao.models;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobin.dao.services.CatalogService;
import com.mobin.dto.mapper.DataMapper;

public class CatalogTest extends BaseTest{

	@Autowired
	CatalogService catalogService;
	DataMapper dataMapper;
	
	@Before
	public void before(){
		Mapper mapper =  new DozerBeanMapper();
		dataMapper = new DataMapper(mapper, null);
	}
	
	@Test
	public void testListCatalog(){
		List<Catalog> catalogList = catalogService.getCatalogList();
		for(Catalog catalog:catalogList){
			System.out.println("ID:: "+catalog.getId());
			System.out.println("Name:: "+catalog.getName());
			System.out.println("Description:: "+catalog.getDescription());
		}
	}
}
