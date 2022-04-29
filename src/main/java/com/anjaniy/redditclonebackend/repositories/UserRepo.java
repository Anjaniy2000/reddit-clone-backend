package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
