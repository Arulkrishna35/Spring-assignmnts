package com.product.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.app.entity.Product;
import com.product.app.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	public List<Product> listAll() {
		return repository.findAll();
	}

	public void save(Product product) {
		repository.save(product);
	}

	public Product get(int id) {
		return repository.findById(id).get();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}