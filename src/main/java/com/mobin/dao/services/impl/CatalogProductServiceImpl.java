package com.mobin.dao.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobin.dao.models.CatalogProduct;
import com.mobin.dao.repositories.CatalogProductRepository;
import com.mobin.dao.services.CatalogProductService;
import com.mobin.utils.ConvertUtils;

@Service
public class CatalogProductServiceImpl implements CatalogProductService {

	private final CatalogProductRepository catalogProductRepository;
	
	@Autowired
	public CatalogProductServiceImpl(CatalogProductRepository catalogProductRepository) {
		// TODO Auto-generated constructor stub
		this.catalogProductRepository = catalogProductRepository;
	}
	
	@Override
	public Map<Integer, List<CatalogProduct>> getCatalogProductsOnCatalogId(List<Integer> catalogIds) {
		// TODO Auto-generated method stub
		List<CatalogProduct> catalogProducts = catalogProductRepository.getCatalogProductsOnCatalogIds(catalogIds);
		return ConvertUtils.convertToMapWithList(catalogProducts, CatalogProduct::getCatalogId);
	}

}
