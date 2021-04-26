package com.example.springboot.springbootcrudrestfulwebservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.springboot.springbootcrudrestfulwebservice.repository.ProductRepository;
import com.example.springboot.springbootcrudrestfulwebservice.exception.RecordNotFoundException;
import com.example.springboot.springbootcrudrestfulwebservice.model.Product;

@CrossOrigin(origins = {
	"http://localhost:3000"
})
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
		
	}
	
	 @GetMapping("/products/{id}")
	    public ResponseEntity<Optional<Product>> getProductByid(@PathVariable(value = "id") Long productId)
	    {
	        Optional<Product> product = productRepository.findById(productId);
	            
	        return ResponseEntity.ok().body(product);
	    }
	 
	 @PutMapping("/products/{id}")
	    public ResponseEntity < Product > updateProduct(@PathVariable(value = "id") Long productId,
	      @RequestBody Product productDetails) throws RecordNotFoundException {
	        Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new RecordNotFoundException("Employee not found for this id :: " + productId));

	        product.setId(productDetails.getId());
	        product.setName(productDetails.getName());
	        product.setType(productDetails.getType());
	        product.setCompany(productDetails.getCompany());
	        final Product updatedProduct = productRepository.save(product);
	        return ResponseEntity.ok(updatedProduct);
	    }
	 
	 @DeleteMapping("/products/{id}")
	    public Map < String, Boolean > deleteProduct(@PathVariable(value = "id") Long productId)
	    throws RecordNotFoundException {
	        Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new RecordNotFoundException("Employee not found for this id :: " + productId));

	        productRepository.delete(product);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}
