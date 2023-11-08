package com.example.article.Controller;

import com.example.article.DTO.ArticleDTO;
import com.example.article.DTO.CommentDTO;
import com.example.article.Service.ArticleService;
import com.example.article.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/list")
    public String list(@PageableDefault(page=1)Pageable pageable, Model model) throws Exception {
        Page<ArticleDTO> articleDTOS=articleService.list(pageable);

        int blockLimit=10;

        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1)*blockLimit+1;

        int endPage=Math.min((startPage+blockLimit-1), articleDTOS.getTotalPages());

        model.addAttribute("list",articleDTOS);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "article/list";
    }

    @GetMapping("/{no}")
    public String detail(@PathVariable Integer no, Model model) {
        ArticleDTO articleDTO=articleService.listOne(no);
        articleDTO.setViewcnt(articleDTO.getViewcnt()+1);
        articleService.modify(articleDTO);
        List<CommentDTO> commentDTOS=commentService.commentList(no);

        model.addAttribute("articleDTO", articleDTO);
        model.addAttribute("commentDTOS", commentDTOS);
        model.addAttribute("no",no);

        return "article/detail";
    }

    @GetMapping("/register")
    public String registerForm(ArticleDTO articleDTO) {
        return "article/register";
    }

    @PostMapping("/register")
    public String register(@Valid ArticleDTO articleDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
            }
            return "article/register";
        }
        articleDTO.setViewcnt(0);
        articleService.register(articleDTO);

        return "redirect:/article/list";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer no, Model model) {
        ArticleDTO articleDTO=articleService.listOne(no);
        model.addAttribute("articleDTO",articleDTO);
        return "article/modify";
    }

    @PostMapping("/modify")
    public String modify(@Valid ArticleDTO articleDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
            }
            return "article/modify";
        }
        articleService.modify(articleDTO);
        return "redirect:/article/list";
    }

    @GetMapping("/delete")
    public String delete(Integer no) {
        articleService.delete(no);
        return "redirect:/article/list";
    }
}
