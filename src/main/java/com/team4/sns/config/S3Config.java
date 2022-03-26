package com.team4.sns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${s3.access-key}")
    private String awsAccessKey;

    @Value("${s3.secret-key}")
    private String awsSecretKey;

    @Bean
    public StaticCredentialsProvider getAwsBasicCredentials(){
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(awsAccessKey, awsSecretKey)
        );
    }

    @Bean
    public S3Client getS3Client(){
        return S3Client.builder()
                .credentialsProvider(getAwsBasicCredentials())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
}
