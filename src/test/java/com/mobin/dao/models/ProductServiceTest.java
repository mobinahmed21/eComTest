package com.mobin.dao.models;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.mobin.dao.services.ProductService;
import com.mobin.dto.mapper.DataMapper;

public class ProductServiceTest extends BaseTest {

	@Autowired
	ProductService productService;
	DataMapper dataMapper;
	
	@Before
	public void before(){
		Mapper mapper =  new DozerBeanMapper();
		dataMapper = new DataMapper(mapper, null);
	}
	
	@Test
	public void testListProducts(){
		List<Integer> productIds = Lists.newArrayList(1,2,3);
		Map<Integer, Product> productsMap = productService.getProductMapFromIds(productIds);
		for(Integer productId:productIds){
			Product product = productsMap.get(productId);
			System.out.println("NAME:: "+product.getName());
		    System.out.println("DESCRIPTION:: "+product.getDescription());
		}
	}
}
