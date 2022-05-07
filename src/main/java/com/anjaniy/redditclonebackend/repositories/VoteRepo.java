package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
