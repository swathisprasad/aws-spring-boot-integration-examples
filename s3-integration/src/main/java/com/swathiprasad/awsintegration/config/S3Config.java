package com.swathiprasad.awsintegration.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.s3.bucketName}")
    private String bucket;

    @Value("${aws.credentials.access}")
    private String awsAccessKeyId;

    @Value("${aws.credentials.secret}")
    private String awsKeySecret;

    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                awsAccessKeyId,
                awsKeySecret
        );
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(awsRegion)
                .build();
    }

    @Bean(name = "bucket")
    public String getBucket() {
        return bucket;
    }
}
