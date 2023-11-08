package com.example.article.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GlobalController {

    @GetMapping("/")
    public String main() {
        return "index";
    }
}
