package com.service.mstc.service.impl;

import com.service.mstc.model.NewsLetter;
import com.service.mstc.repository.NewsLetterRepository;
import com.service.mstc.service.MailService;
import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private NewsLetterRepository newsLetterRepository;

  @Autowired
  private SpringTemplateEngine springTemplateEngine;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final String EMAIL = "email";

  private static final String from = "spring.email.username";

  private final Logger log = LoggerFactory.getLogger(MailService.class);

  public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
    log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

    // Prepare message using a Spring helper
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
      message.setTo(to);
//            message.setFrom(jHipsterProperties.getMail().getFrom());
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

  public void sendEmailFromTemplate(String email, String templateName) {
    NewsLetter newsLetter = newsLetterRepository.findByEmail(email);
    Locale locale = Locale.forLanguageTag("en");
    Context context = new Context(locale);
    context.setVariable(EMAIL, newsLetter);
    String content = springTemplateEngine.process(templateName, context);
    String subject = "Congratulation! You've successfully received email from MSTC";
    sendEmail(email, subject, content, false, true);
  }


  @Override
  public void save(NewsLetter email) {
    if(!newsLetterRepository.existsByEmail(email.getEmail())){
      newsLetterRepository.save(email);
    }
    sendEmailFromTemplate(email.getEmail(), "newsEmail");
  }
}
