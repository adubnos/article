package com.example.article.Controller;

import com.example.article.DTO.DebateDTO;
import com.example.article.DTO.OpinionDTO;
import com.example.article.Entity.Debate;
import com.example.article.Service.DebateService;
import com.example.article.Service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/debate")
public class DebateController {
    private final DebateService debateService;
    private final OpinionService opinionService;

    @GetMapping("/list")
    public String list(@PageableDefault(page=1)Pageable pageable, Model model) {
        Page<DebateDTO> debateDTOS=debateService.debateDTOS(pageable);

        int blockLimit=5;

        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1)*blockLimit+1;
        int endPage=Math.min((startPage+blockLimit-1), debateDTOS.getTotalPages());

        model.addAttribute("debateDTOS", debateDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "debate/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = false) Boolean view, Integer debateId, HttpSession session, Model model) {
        if (view != null && view && session.getAttribute("visited") == null) {
            debateService.viewcntInc(debateId);
            session.setAttribute("visited", true);
        }
        DebateDTO debateDTO=debateService.listOne(debateId);
        List<OpinionDTO> opinionDTOS=opinionService.opinionDTOS(debateId);

        model.addAttribute("debateDTO",debateDTO);
        model.addAttribute("opinionDTOS",opinionDTOS);
        return "debate/detail";
    }

    @GetMapping("/register")
    public String registerForm(DebateDTO debateDTO) {
        return "debate/register";
    }

    @PostMapping("/register")
    public String register(@Valid DebateDTO debateDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
            }
            return "debate/register";
        }
        debateService.register(debateDTO);
        return "redirect:/debate/list";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer debateId, Model model) {
        DebateDTO debateDTO=debateService.listOne(debateId);
        model.addAttribute("debateDTO",debateDTO);
        return "debate/modify";
    }

    @PostMapping("/modify")
    public String modify(@Valid DebateDTO debateDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
            }
            return "debate/modify";
        }
        debateService.modify(debateDTO);
        return "redirect:/debate/list";
    }

    @GetMapping("/delete")
    public String delete(Integer debateId) {
        debateService.delete(debateId);
        return "redirect:/debate/list";
    }
}
