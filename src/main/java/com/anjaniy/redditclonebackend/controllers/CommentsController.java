package com.anjaniy.redditclonebackend.controllers;

import com.anjaniy.redditclonebackend.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    public void createComment(@RequestBody CommentDto commentDto){

    }
}
