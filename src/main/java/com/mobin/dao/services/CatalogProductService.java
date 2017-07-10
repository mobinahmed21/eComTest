package com.mobin.dao.services;

import java.util.List;
import java.util.Map;

import com.mobin.dao.models.CatalogProduct;

public interface CatalogProductService {

	Map<Integer, List<CatalogProduct>> getCatalogProductsOnCatalogId(List<Integer> catalogIds);
}
