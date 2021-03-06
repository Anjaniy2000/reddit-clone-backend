package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepo extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String subredditName);
}
