package com.assignment.crud.application.service;

import java.util.List;

import com.assignment.crud.application.dto.ProductRequest;
import com.assignment.crud.application.dto.ResponseStatus;
import com.assignment.crud.application.entity.Product;

public interface ProductService {
	Product addProduct(ProductRequest request);

	List<Product> findAllPrdoducts();

	Product findProductById(Long id);

	Product updateProductById(Long id, ProductRequest request);
	
	ResponseStatus deleteProductById(Long id);

}
