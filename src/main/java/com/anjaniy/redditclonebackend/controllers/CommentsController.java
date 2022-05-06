package com.anjaniy.redditclonebackend.controllers;

import com.anjaniy.redditclonebackend.dto.CommentDto;
import com.anjaniy.redditclonebackend.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable("userName") String userName) {
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForUser(userName));
    }
}
