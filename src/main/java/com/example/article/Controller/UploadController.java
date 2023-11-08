package com.example.article.Controller;

import com.example.article.Service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class UploadController {

    private final UploadService uploadService;

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        return "upload/upload";
    }

    @PostMapping("/upload")
    public String upload(String subject, MultipartFile file, Model model) throws IOException {
        System.out.println("받은 파일 명 : "+file.getOriginalFilename());
        String newFileName=uploadService.saveImg(file);
        System.out.println("저장시 사용한 파일 명 : "+newFileName);

        model.addAttribute("newFileName","/images/item/"+newFileName);
        model.addAttribute("imgFile",newFileName);
        return "upload/list";
    }
}
