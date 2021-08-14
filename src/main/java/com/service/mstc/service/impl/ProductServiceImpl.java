package com.service.mstc.service.impl;

import com.service.mstc.dto.ProductDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.exception.BadRequestException;
import com.service.mstc.model.Article;
import com.service.mstc.model.Product;
import com.service.mstc.repository.ProductRepository;
import com.service.mstc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public DataListDto get(ProductDto pageableDto) {
    Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
    Page<Product> products = null;
    String brand = pageableDto.getBrand();
    String country = pageableDto.getCountry();
    String category = pageableDto.getCategory();

//    check existence of params
    if(country == null && brand == null && category == null){
      products = productRepository.findAll(pageable);
    }
    if(country == null && brand == null && category != null){
       products = productRepository.findAllByCategory(category, pageable);
    }
    if(country == null && brand != null && category == null){
      products = productRepository.findAllByBrand(brand, pageable);
    }
    if(country != null && brand == null && category == null){
      products = productRepository.findAllByCountry(country, pageable);
    }
    if(country != null && brand != null && category == null){
      products = productRepository.findAllByCountryAndBrand(country, brand, pageable);
    }
    if(country == null && brand != null && category != null){
      products = productRepository.findAllByCategoryAndBrand(category, brand, pageable);
    }
    if(country != null && brand == null && category != null){
      products = productRepository.findAllByCountryAndCategory(country, category, pageable);
    }
    if(country != null && brand != null && category != null){
      products = productRepository.findAllByCountryAndBrandAndCategory(country, brand, category, pageable);
    }

    if(brand == null && category == null && country == null){
      products = productRepository.findAll(pageable);
    }

    DataListDto data = new DataListDto();
    data.setData(products.getContent());
    data.setPage(pageableDto.getPage());
    data.setSize(pageableDto.getSize());
    data.setTotal(products.getTotalElements());

    return data;
  }

  @Override
  public Product post(Product product) {
    productRepository.findByName(product.getName()).ifPresent(item -> {
      throw new BadRequestException("Product with name:: " + item.getName() + " already exists!");
    });
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    return productRepository.save(product);
  }
}
