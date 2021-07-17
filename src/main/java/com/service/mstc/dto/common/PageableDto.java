package com.service.mstc.dto.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageableDto {
  private int page;

  private int size;

  private int total;
}
