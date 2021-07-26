package com.service.mstc.service.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.service.mstc.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class S3ServiceImpl implements S3Service{
  private final String bucketName = "mstc-bucket";

  @Autowired
  private AmazonS3 amazonS3;

//  upload to s3
  @Override
  @Async
  public String upload(String folder, File file) {
    try {
      String keyName = String.format("%s/%s", folder, file.getName());
      this.amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file));
      //delete file

      file.delete();
      return keyName;
    } catch (AmazonServiceException e) {
      throw new ResourceException(e.getErrorMessage(), file);
    }
  }

  @Override
  public String getEndpoint() {
    return String.format("https://%s.s3.%s.amazonaws.com", bucketName, this.amazonS3.getBucketLocation(bucketName));
  }
}
