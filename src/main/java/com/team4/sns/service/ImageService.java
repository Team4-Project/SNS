package com.team4.sns.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void uploadObject(Long postId, List<MultipartFile> images) throws IOException;
}
