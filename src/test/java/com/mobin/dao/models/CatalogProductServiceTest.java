package com.mobin.dao.models;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.mobin.dao.services.CatalogProductService;
import com.mobin.dto.mapper.DataMapper;
import com.mobin.dto.models.CatalogProductDto;

public class CatalogProductServiceTest extends BaseTest {

	@Autowired
	CatalogProductService catalogProductService;
	DataMapper dataMapper;
	
	@Before
	public void before(){
		Mapper mapper =  new DozerBeanMapper();
		dataMapper = new DataMapper(mapper, null);
	}
	
	@Test
	public void testListCatalogProduct(){
		List<Integer> catalogIds = Lists.newArrayList(1,2);
		Map<Integer, List<CatalogProduct>> catalogproductsMap = catalogProductService.getCatalogProductsOnCatalogId(catalogIds);
		for(Integer catalogId:catalogIds){
			List<CatalogProduct> catalogProducts = catalogproductsMap.get(catalogId);
			for(CatalogProduct catalogProduct:catalogProducts){
				CatalogProductDto catalogProductDto = dataMapper.convertFromEntityToDto(catalogProduct, CatalogProductDto.class);
				System.out.println("CATALOG ID::" + catalogProductDto.getCatalogId());
				System.out.println("PRODUCT ID:: "+catalogProductDto.getProductId());
				System.out.println("CONFIGURABLE:: "+catalogProductDto.getConfigurable());
			}
		}
	}
}
