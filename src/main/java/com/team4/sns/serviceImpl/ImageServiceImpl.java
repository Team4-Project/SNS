package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.ImageMapper;
import com.team4.sns.service.ImageService;
import com.team4.sns.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final S3Util s3Util;
    private final ImageMapper imageMapper;

    @Override
    public void uploadObject(Long postId,List<MultipartFile> images) throws IOException {
        List<String> uploadedImageUrls = s3Util.uploadObject(images);
        imageMapper.insertImageUrl(postId, uploadedImageUrls);
    }
}
