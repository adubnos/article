package com.example.article.Controller;

import com.example.article.DTO.OpinionDTO;
import com.example.article.Service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/register")
    public String register(Integer debateId, OpinionDTO opinionDTO, RedirectAttributes redirectAttributes) {
        opinionService.opinionRegister(opinionDTO, debateId);
        redirectAttributes.addAttribute("debateId", debateId);
        return "redirect:/debate/detail";
    }

    @GetMapping("/good")
    public String good(Integer debateId, Integer opinionId, RedirectAttributes redirectAttributes) {
        opinionService.goodcntInc(opinionId);
        redirectAttributes.addAttribute("debateId", debateId);
        return "redirect:/debate/detail";
    }

    @GetMapping("/bad")
    public String bad(Integer debateId, Integer opinionId, RedirectAttributes redirectAttributes) {
        opinionService.badcntInc(opinionId);
        redirectAttributes.addAttribute("debateId", debateId);
        return "redirect:/debate/detail";
    }

    @PostMapping("/modify")
    public String modify(Integer debateId, Integer opinionId, OpinionDTO opinionDTO, RedirectAttributes redirectAttributes) {
        opinionService.opinionModify(opinionDTO, opinionId, debateId);
        redirectAttributes.addAttribute("debateId", debateId);
        return "redirect:/debate/detail";
    }

    @GetMapping("/delete")
    public String delete(Integer debateId, Integer opinionId, RedirectAttributes redirectAttributes) {
        opinionService.opinionDelete(opinionId);
        redirectAttributes.addAttribute("debateId", debateId);
        return "redirect:/debate/detail";
    }

}
