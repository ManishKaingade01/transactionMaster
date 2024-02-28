package com.assignment.crud.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.crud.application.dto.ProductRequest;
import com.assignment.crud.application.service.ProductService;

@RestController
@RequestMapping("/v1/products/")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String test() {
		return "Working";
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
		return ResponseEntity.ok(productService.addProduct(request));
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllProduct() {
		return ResponseEntity.ok(productService.findAllPrdoducts());
	}

	@GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProductById(@PathVariable(name = "productId") Long productId) {
		return ResponseEntity.ok(productService.findProductById(productId));
	}

	@PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProductById(@RequestBody ProductRequest request,
			@PathVariable(name = "productId") Long productId) {

		return ResponseEntity.ok(productService.updateProductById(productId, request));
	}
	
	@DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteProductById(@PathVariable(name = "productId") Long productId) {
		return ResponseEntity.ok(productService.deleteProductById(productId));
	}

}
