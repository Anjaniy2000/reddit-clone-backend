package com.anjaniy.redditclonebackend.repositories;

import com.anjaniy.redditclonebackend.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
}
