package com.mobin.dao.services;

import java.util.List;
import java.util.Map;

import com.mobin.dao.models.Product;

public interface ProductService {

	Map<Integer, Product> getProductMapFromIds(List<Integer> productIds);
}
