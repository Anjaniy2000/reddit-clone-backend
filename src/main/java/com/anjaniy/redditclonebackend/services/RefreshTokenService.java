package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.exceptions.SpringRedditException;
import com.anjaniy.redditclonebackend.models.RefreshToken;
import com.anjaniy.redditclonebackend.repositories.RefreshTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepo;

    RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepo.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepo.deleteByToken(token);
    }
}
