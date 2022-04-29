package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
}
