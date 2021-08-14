package com.service.mstc.repository;

import com.service.mstc.enums.Status;
import com.service.mstc.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
  Optional<Article> findById(String id);
  Optional<Article> findBySlug(String slug);
  Page<Article> findAllByStatus(Status status, Pageable pageable);
  Page<Article> findAll(Pageable pageable);
}
