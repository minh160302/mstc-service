package com.service.mstc.service;

import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.dto.common.PageableDto;
import com.service.mstc.model.Email;

import java.util.List;

public interface MailService {
//  void save(Email email);

  DataListDto get(PageableDto pageableDto);

  void delete(String id);

  Email update(Email email);

  Email getById(String id);

  Email post(Email email);

  void send(List<String> email);
}
