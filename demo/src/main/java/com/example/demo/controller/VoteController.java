package com.example.demo.controller;

import com.example.demo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }

    @PostMapping("/add")
    public String addVote(@RequestParam Integer pictureId, @RequestParam Integer value, HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/users/login";
        voteService.addVote((Integer) session.getAttribute("userId"), pictureId, value);
        return "redirect:/pictures";
    }

}