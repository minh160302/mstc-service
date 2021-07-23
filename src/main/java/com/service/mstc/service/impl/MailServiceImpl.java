package com.service.mstc.service.impl;

import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.dto.common.PageableDto;
import com.service.mstc.exception.BadRequestException;
import com.service.mstc.model.Email;
import com.service.mstc.repository.EmailRepository;
import com.service.mstc.service.MailService;
import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private EmailRepository emailRepository;

  @Autowired
  private SpringTemplateEngine springTemplateEngine;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final String EMAIL = "email";

  private static final String from = "spring.email.username";

  private final Logger log = LoggerFactory.getLogger(MailService.class);

  public void sendEmail(List<String> to, String subject, String content, boolean isMultipart, boolean isHtml) {
    log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

    // Prepare message using a Spring helper
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
//      cast type to String[]
      message.setTo(to.toArray(new String[0]));
//      message.setFrom(jHipsterProperties.getMail().getFrom());
      message.setFrom(from, "MSTC");
      message.setSubject(subject);
      message.setText(content, isHtml);
      javaMailSender.send(mimeMessage);
      log.debug("Sent email to User '{}'", to);
    } catch (Exception e) {
      if (log.isDebugEnabled()) {
        log.warn("Email could not be sent to user '{}'", to, e);
      } else {
        log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
      }
    }
  }

  public void sendEmailFromTemplate(List<String> emails, String templateName) {
    Locale locale = Locale.forLanguageTag("en");
    Context context = new Context(locale);
    context.setVariable(EMAIL, "promotions email");
    String content = springTemplateEngine.process(templateName, context);
    String subject = "Congratulation! You've successfully received email from MSTC";
    sendEmail(emails, subject, content, false, true);
  }


  @Override
  public void send(List<String> email) {
    sendEmailFromTemplate(email, "newsEmail");
  }

  @Override
  public DataListDto get(PageableDto pageableDto) {
    Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
    Page<Email> emails = emailRepository.findAll(pageable);

    DataListDto data = new DataListDto();
    data.setData(emails.getContent());
    data.setPage(pageableDto.getPage());
    data.setSize(pageableDto.getSize());
    data.setTotal(emails.getTotalElements());

    return data;
  }

  @Override
  public void delete(String id) {
    emailRepository.deleteById(id);
  }

  @Override
  public Email update(Email email) {
    emailRepository.findById(email.getId()).orElseThrow(() -> new BadRequestException("Email not exists"));

    List<Email> emailList = emailRepository.findAll()
            .stream()
            .filter(e -> !e.getEmail().equals(email.getEmail())).collect(Collectors.toList());

    for (Email item : emailList) {
      if (item.getEmail().equals(email.getEmail())) {
        throw new BadRequestException("Email: " + item.getEmail() + " already exists!");
      }
    }

    return emailRepository.save(email);
  }

  @Override
  public Email getById(String id) {
    return emailRepository.findById(id).orElseThrow(() -> new BadRequestException("Email not exists"));
  }

  @Override
  public Email post(Email email) {
    emailRepository.findByEmail(email.getEmail())
            .ifPresent((e) -> {
              throw new BadRequestException(e.getEmail() + " already exists");
            });

    return emailRepository.save(email);
  }
}
