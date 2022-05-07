package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.VoteDto;
import com.anjaniy.redditclonebackend.exceptions.PostNotFoundException;
import com.anjaniy.redditclonebackend.exceptions.SpringRedditException;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.Vote;
import com.anjaniy.redditclonebackend.repositories.PostRepo;
import com.anjaniy.redditclonebackend.repositories.VoteRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.anjaniy.redditclonebackend.models.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    @Autowired
    private VoteRepo voteRepository;
    @Autowired
    private PostRepo postRepository;
    @Autowired
    private AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {

        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
