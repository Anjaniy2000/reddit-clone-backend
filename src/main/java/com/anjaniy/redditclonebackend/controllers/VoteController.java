package com.anjaniy.redditclonebackend.controllers;

import com.anjaniy.redditclonebackend.dto.VoteDto;
import com.anjaniy.redditclonebackend.services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
@Tag(name = "Vote REST Endpoint")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    @Operation(summary = "Endpoint For Voting (UPVOTE / DOWNVOTE).")
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}