package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Comment;
import com.anjaniy.redditclonebackend.models.Post;
import com.anjaniy.redditclonebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
