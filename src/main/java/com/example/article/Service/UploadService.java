package com.example.article.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class UploadService {
    @Value("${imgLocation}")
    private String imgLocation;

    private final ImgService imgService;

    public String saveImg(MultipartFile imgFile) throws IOException {
        String originalFileName=imgFile.getOriginalFilename();
        String newFileName="";

        if(!StringUtils.isEmpty(originalFileName)) {
            newFileName=imgService.uploadImg(imgLocation, originalFileName, imgFile.getBytes());
        }
        return newFileName;
    }
}
