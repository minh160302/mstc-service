package com.service.mstc.repository;

import com.service.mstc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
  Optional<Product> findByName(String name);

  Page<Product> findAllByCountry(String country, Pageable pageable);
  Page<Product> findAllByBrand(String brand, Pageable pageable);
  Page<Product> findAllByCategory(String category, Pageable pageable);

  Page<Product> findAllByCountryAndBrand(String country, String brand, Pageable pageable);
  Page<Product> findAllByCountryAndCategory(String country, String category, Pageable pageable);
  Page<Product> findAllByCategoryAndBrand(String category, String brand, Pageable pageable);

  Page<Product> findAllByCountryAndBrandAndCategory(String country, String brand, String category, Pageable pageable);

  Page<Product> findAll(Pageable pageable);
}
