package com.example.article.Controller;

import com.example.article.DTO.CommentDTO;
import com.example.article.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/article/{no}/comment")
    public String registerComment(@PathVariable Integer no, CommentDTO commentDTO) {
        commentService.commentRegister(commentDTO, no);

        return "redirect:/article/"+no;
    }

    @PostMapping("/article/{no}/comment/{commentNo}/modify")
    public String modifyComment(@PathVariable Integer no, @PathVariable Integer commentNo, CommentDTO commentDTO) {

        commentService.commentModify(commentDTO, commentNo, no);
        return "redirect:/article/"+no;
    }

    @GetMapping("/article/{no}/comment/{commentNo}/delete")
    public String deleteComment(@PathVariable Integer no, @PathVariable Integer commentNo) {
        commentService.delete(commentNo);
        return "redirect:/article/"+no;
    }
}
