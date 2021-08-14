package com.service.mstc.service;

import com.service.mstc.dto.ProductDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.model.Product;

public interface ProductService {
  DataListDto get(ProductDto productDto);

  Product post(Product product);

  Product update(Product product);
}
