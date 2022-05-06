package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.CommentDto;
import com.anjaniy.redditclonebackend.exceptions.PostNotFoundException;
import com.anjaniy.redditclonebackend.mappers.CommentMapper;
import com.anjaniy.redditclonebackend.models.Comment;
import com.anjaniy.redditclonebackend.models.NotificationEmail;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.repositories.CommentRepo;
import com.anjaniy.redditclonebackend.repositories.PostRepo;
import com.anjaniy.redditclonebackend.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepo commentRepo;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    private static final String POST_URL = "";

    public void save(CommentDto commentDto){
        Post post = postRepo.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));

        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());

        commentRepo.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " Posted a comment on your post. " + POST_URL);
        sendCommentNotification(message, post.getUser());

    }

    private void sendCommentNotification(String message, User user){
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepo.findByPost(post).stream().map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepo.findByUsername(userName).orElseThrow(() -> new PostNotFoundException(userName));
        return commentRepo.findAllByUser(user).stream().map(commentMapper::mapToDto).collect(toList());
    }
}
