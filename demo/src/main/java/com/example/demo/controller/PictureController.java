package com.example.demo.controller;

import com.example.demo.models.Picture;
import com.example.demo.models.Users;
import com.example.demo.service.PictureService;
import com.example.demo.service.UsersService;
import com.example.demo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/pictures")
public class PictureController {
    private final PictureService pictureService;
    private final UsersService usersService;
    private final VoteService voteService;

    public PictureController(PictureService pictureService, UsersService usersService, VoteService voteService){
        this.pictureService = pictureService;
        this.usersService = usersService;
        this.voteService = voteService;
    }

    @GetMapping("")
    public String getAll(Model model, HttpSession session){
        Integer user = (Integer) session.getAttribute("userId");
        if (user == null)
            return "redirect:/users/login";
        model.addAttribute("pictures", pictureService.getAllPictures());
        model.addAttribute("currentUserId", user);
        model.addAttribute("voteTotals", voteService.getTotalVotes());
        return "pictures";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id, Model model, HttpSession session){
        Integer user = (Integer) session.getAttribute("userId");
        if (user == null)
            return ResponseEntity.status(401).build();
        Picture picture = pictureService.getPictureById(id);
        if (picture == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(picture.getContentType()))
                .body(picture.getImageData());
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return "redirect:/users/login";
        Users user = usersService.findById(userId);
        pictureService.upload(file, user);
        return "redirect:/pictures";
    }

}
