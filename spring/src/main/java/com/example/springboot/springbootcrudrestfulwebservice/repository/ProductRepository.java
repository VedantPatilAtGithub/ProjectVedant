package com.example.springboot.springbootcrudrestfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.springbootcrudrestfulwebservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
