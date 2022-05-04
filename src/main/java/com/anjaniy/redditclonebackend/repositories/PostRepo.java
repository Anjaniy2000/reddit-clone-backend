package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
