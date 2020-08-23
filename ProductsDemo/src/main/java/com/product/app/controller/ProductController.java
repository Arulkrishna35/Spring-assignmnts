package com.product.app.controller;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.product.app.entity.Product;
import com.product.app.exception.ProductException;
import com.product.app.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Swagger2DemoRestController")
@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	@ApiOperation(value = "Get list of products in the System ", response = Iterable.class, tags = "getProduct")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	
	 @GetMapping("/product")
	 public ResponseEntity<List<Product>> list() {
			return new ResponseEntity<List<Product>>(service.listAll(),HttpStatus.OK);
	 }

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> get(@PathVariable int id) {
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Product product) {
		service.save(product);
		return new ResponseEntity<String>("PRODUCT ADDED SUCCESSFULLY",HttpStatus.OK);
	}
	@DeleteMapping("/delete")  
	private ResponseEntity<String> delete(@RequestBody int id)   
	{  
		service.delete(id);  
		return new ResponseEntity<String>( "PRODUCT DELETED SUCCESSFULLY",HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Product updateProduct) {
		try {
			Product product=service.get(updateProduct.getId());
			service.save(updateProduct);
			return new ResponseEntity<String>( "PRODUCT UPDATED SUCCESSFULLY",HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new ProductException("NOT A VALID PRODUCT ID [e.g custom exception]");
			//return "Not a valid course id";
		}
	}
	
	

}
