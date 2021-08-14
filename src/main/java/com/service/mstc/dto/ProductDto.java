package com.service.mstc.dto;

import com.service.mstc.dto.common.PageableDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends PageableDto {
  private String brand;
  private String country;
  private String category;
}
