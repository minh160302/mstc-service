package com.service.mstc.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataListDto {
  private Integer page;

  // Field descriptor #10 Ljava/lang/Integer;
  private Integer size;

  // Field descriptor #10 Ljava/lang/Integer;
  private Long total;

  // Field descriptor #14 Ljava/util/List;
  private List data;
}
