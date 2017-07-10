package com.mobin.dao.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobin.dao.models.Product;
import com.mobin.dao.repositories.ProductRepository;
import com.mobin.dao.services.ProductService;
import com.mobin.utils.ConvertUtils;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	@Override
	public Map<Integer, Product> getProductMapFromIds(List<Integer> productIds) {
		// TODO Auto-generated method stub
		return ConvertUtils.convertToMap(productRepository.getProductsFromIds(productIds), Product::getId);
	}

}
