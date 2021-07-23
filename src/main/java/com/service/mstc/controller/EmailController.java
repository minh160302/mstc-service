package com.service.mstc.controller;

import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.dto.common.PageableDto;
import com.service.mstc.model.Email;
import com.service.mstc.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

  @Autowired
  private MailService mailService;

  @GetMapping
  public DataListDto get(PageableDto pageableDto){
    return mailService.get(pageableDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id){
    mailService.delete(id);
  }

  @GetMapping("/{id}")
  public Email getById(@PathVariable String id){
    return mailService.getById(id);
  }

  @PutMapping
  public Email update(@RequestBody Email email){
    return mailService.update(email);
  }

  @PostMapping
  public Email post(@RequestBody Email email) {
    return mailService.post(email);
  }

  @PostMapping("/send")
  public void sendEmail(@RequestBody List<String> email){
    mailService.send(email);
  }
}
