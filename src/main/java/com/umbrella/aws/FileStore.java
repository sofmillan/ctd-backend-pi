package com.umbrella.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileStore {
    private final S3Client s3;

    public String save(String fileName,
                       InputStream inputStream, String contentType, long size) {


        PutObjectRequest putObjectRequest =  PutObjectRequest.builder()
                .bucket("ticketgo-api")
                .key(fileName)
                .contentType(contentType)
                .acl("public-read")
                .build();
        s3.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, size));

        return "https://ticketgo-api.s3.us-east-1.amazonaws.com/"+fileName;


    }
}
