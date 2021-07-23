package com.service.mstc.repository;

import com.service.mstc.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
  boolean existsByEmail(String email);
  Optional<Email> findByEmail(String email);
  Page<Email> findAll(Pageable pageable);
}
