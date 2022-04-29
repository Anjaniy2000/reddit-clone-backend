package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
