package com.service.mstc.controller;

import com.service.mstc.dto.ProductDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.model.Product;
import com.service.mstc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public DataListDto get(ProductDto pageableDto){
    return productService.get(pageableDto);
  }

  @PostMapping
  public Product post(@RequestBody Product product){
    return productService.post(product);
  }

  @PutMapping
  public Product update(@RequestBody Product product){
    return productService.update(product);
  }
}
