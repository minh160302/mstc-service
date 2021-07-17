package com.service.mstc.service.impl;

import com.service.mstc.dto.PageStatusDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.exception.BadRequestException;
import com.service.mstc.model.Article;
import com.service.mstc.repository.ArticleRepository;
import com.service.mstc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public DataListDto get(PageStatusDto pageableDto) {
    Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
    Page<Article> articles = null;
    if (pageableDto.getStatus() != null) {
      articles = articleRepository.findAllByStatus(pageableDto.getStatus(), pageable);
    } else {
      articles = articleRepository.findAll(pageable);
    }

    DataListDto data = new DataListDto();
    data.setData(articles.getContent());
    data.setPage(pageableDto.getPage());
    data.setSize(pageableDto.getSize());
    data.setTotal(articles.getTotalElements());

    return data;
  }

  @Override
  public Article save(Article article) {
    articleRepository.findBySlug(article.getSlug()).ifPresent(item -> {
      throw new BadRequestException("Slug:: " + item.getSlug() + " is already in use!");
    });
    article.setUpdatedDate(Instant.now());
    return articleRepository.save(article);
  }

  @Override
  public Article update(Article article) {
    articleRepository.findById(article.getId()).orElseThrow(() -> new BadRequestException("Article not found!"));

    articleRepository.findBySlug(article.getSlug()).ifPresent(item -> {
      throw new BadRequestException("Slug:: " + item.getSlug() + " is already in use!");
    });

    article.setUpdatedDate(Instant.now());
    return articleRepository.save(article);
  }

  @Override
  public void delete(String id) {
    articleRepository.findById(id).orElseThrow(() -> new BadRequestException("Article not found!"));

    articleRepository.deleteById(id);
  }

  @Override
  public Article getBySlug(String slug) {
    return articleRepository.findBySlug(slug).orElseThrow(() -> new BadRequestException("Article not found!"));
  }

}
