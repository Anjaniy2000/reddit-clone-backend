package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
