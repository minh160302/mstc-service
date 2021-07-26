package com.service.mstc.repository;

import com.service.mstc.model.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UploadRepository extends MongoRepository<Upload, String> {
}
