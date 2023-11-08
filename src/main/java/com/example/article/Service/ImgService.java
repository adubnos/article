package com.example.article.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImgService {

    public String uploadImg(String uploadPath, String originalFileName, byte[] fileData) throws IOException {

        UUID uuid=UUID.randomUUID();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        String imageFileName=uuid+extension;
        String fileuploadFullURL=uploadPath+imageFileName;

        FileOutputStream fos= new FileOutputStream(fileuploadFullURL);
        fos.write(fileData);
        fos.close();

        return imageFileName;

    }

    public void deleteImg(String filePath) {
        File deleteImg = new File(filePath);
        if(deleteImg.exists()) {
            deleteImg.delete();
        }
    }
}
