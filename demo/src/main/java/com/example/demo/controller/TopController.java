package com.example.demo.controller;

import com.example.demo.models.Picture;
import com.example.demo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/top")
public class TopController {
    private final VoteService voteService;

    public TopController(VoteService voteService){
        this.voteService = voteService;
    }

    @GetMapping("")
    public String get(HttpSession session, Model model){
        if (session.getAttribute("userId") == null)
            return "redirect:/user/login";
        return "top";
    }

    @PostMapping("")
    public String getTopN(@RequestParam Integer n, HttpSession session, Model model){
        if (session.getAttribute("userId") == null)
            return "redirect:/user/login";
        model.addAttribute("topPictures", voteService.getTopN(n));
        return "top";
    }
}
