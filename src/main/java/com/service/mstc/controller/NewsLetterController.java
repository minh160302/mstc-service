package com.service.mstc.controller;

import com.service.mstc.model.NewsLetter;
import com.service.mstc.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsLetterController {

  @Autowired
  private MailService mailService;

  @PostMapping
  public void registerNews(@RequestBody NewsLetter email){
    mailService.save(email);
  }
}
