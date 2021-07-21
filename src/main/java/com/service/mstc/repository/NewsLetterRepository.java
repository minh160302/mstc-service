package com.service.mstc.repository;

import com.service.mstc.model.NewsLetter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLetterRepository extends MongoRepository<NewsLetter, String> {
  boolean existsByEmail(String email);
  NewsLetter findByEmail(String email);
}
