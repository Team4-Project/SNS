package com.team4.sns.util;

import com.team4.sns.config.S3Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3Util {

    private static final String BUCKET_NAME = "codepresso-team4";
    private final S3Config s3Config;

    public String uploadObject(MultipartFile data) throws IOException {
        S3Client s3Client = s3Config.getS3Client();

        byte[] byteArr = data.getBytes();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(data.getOriginalFilename())
                .contentType(MediaType.ALL_VALUE)
                .contentLength((long) byteArr.length)
                .build();
        log.info("data : {}", data.getOriginalFilename());
        PutObjectResponse putObjectResult = s3Client.putObject(
                request,
                RequestBody.fromByteBuffer(ByteBuffer.wrap(byteArr)));

        URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(BUCKET_NAME).key(data.getOriginalFilename()).build());
        log.info("putObjectResult : {}", putObjectResult);
        log.info("reportUrl : {}", reportUrl);
        return reportUrl.toString();
    }

    public List<String> getObjectList() {
        S3Client s3Client = s3Config.getS3Client();

        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(BUCKET_NAME)
                .build();

        List<String> keyList = new ArrayList<>();

        List<S3Object> response = s3Client.listObjects(request).contents();
        for (S3Object s3Object : response) {
            keyList.add(s3Object.key());
        }

        return keyList;
    }

    public boolean deleteObject(String key) {
        S3Client s3Client = s3Config.getS3Client();

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
        return response.deleteMarker();
    }
}
