package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.PostRequest;
import com.anjaniy.redditclonebackend.dto.PostResponse;
import com.anjaniy.redditclonebackend.exceptions.PostNotFoundException;
import com.anjaniy.redditclonebackend.exceptions.SubredditNotFoundException;
import com.anjaniy.redditclonebackend.mappers.PostMapper;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.repositories.PostRepo;
import com.anjaniy.redditclonebackend.repositories.SubredditRepo;
import com.anjaniy.redditclonebackend.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepo subredditRepo;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepo.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepo.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepo.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepo.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepo.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepo.findByUser(user).stream().map(postMapper::mapToDto).collect(toList());
    }
}
