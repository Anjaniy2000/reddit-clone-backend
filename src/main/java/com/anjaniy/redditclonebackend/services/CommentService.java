package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.CommentDto;
import com.anjaniy.redditclonebackend.exceptions.PostNotFoundException;
import com.anjaniy.redditclonebackend.mappers.CommentMapper;
import com.anjaniy.redditclonebackend.models.Comment;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.repositories.CommentRepo;
import com.anjaniy.redditclonebackend.repositories.PostRepo;
import com.anjaniy.redditclonebackend.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepo commentRepo;

    public void save(CommentDto commentDto){
        Post post = postRepo.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));

        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());

        commentRepo.save(comment);

    }
}
