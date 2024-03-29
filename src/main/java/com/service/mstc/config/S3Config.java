package com.service.mstc.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
  @Bean
  public AmazonS3 s3() {
    AWSCredentials awsCredentials =
            new BasicAWSCredentials("AKIAXORINMPXN5UBXTRM", "YvemyG9HeCfvhcfnA/W0eJr0R/+zHDuhpGJzwX5O");
    return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.AP_SOUTHEAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();
  }
}
