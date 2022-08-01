package com.anjaniy.redditclonebackend.controllers;

import com.anjaniy.redditclonebackend.dto.PostRequest;
import com.anjaniy.redditclonebackend.dto.PostResponse;
import com.anjaniy.redditclonebackend.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Slf4j
@Tag(name = "Post REST Endpoint")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @Operation(summary = "Endpoint For Creating Posts.")
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Endpoint To Get All The Posts.")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint To Get A Post By Its Corresponding ID.")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("/by-subreddit/{id}")
    @Operation(summary = "Endpoint To Get All The Posts Of A Particular Subreddit.")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("by-user/{username}")
    @Operation(summary = "Endpoint To Get All The Posts Of A Particular User.")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable("username") String username) {
        log.info(username);
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
