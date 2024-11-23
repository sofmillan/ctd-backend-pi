package com.umbrella.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonConfig {
    @Bean
    public S3Client s3(@Value("${aws.access.key}") String accessKey,
                       @Value("${aws.secret.key}") String secretKey){
        AwsBasicCredentials awsCredentials =  AwsBasicCredentials.create(accessKey,secretKey);
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.US_EAST_1)
                .build();
    }
}
