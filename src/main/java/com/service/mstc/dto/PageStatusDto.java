package com.service.mstc.dto;

import com.service.mstc.dto.common.PageableDto;
import com.service.mstc.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageStatusDto extends PageableDto {
  private Status status;
}
