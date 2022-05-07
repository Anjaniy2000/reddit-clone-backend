package com.anjaniy.redditclonebackend.mappers;

import com.anjaniy.redditclonebackend.dto.PostRequest;
import com.anjaniy.redditclonebackend.dto.PostResponse;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.repositories.CommentRepo;
import com.anjaniy.redditclonebackend.repositories.VoteRepo;
import com.anjaniy.redditclonebackend.services.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostsMapper {

    @Autowired
    private CommentRepo commentRepository;
    @Autowired
    private VoteRepo voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) { return commentRepository.findByPost(post).size(); }

    String getDuration(Post post) { return TimeAgo.using(post.getCreateDate().toEpochMilli()); }
}
