package com.anjaniy.redditclonebackend.controllers;

import com.anjaniy.redditclonebackend.dto.AuthenticationResponse;
import com.anjaniy.redditclonebackend.dto.LoginRequest;
import com.anjaniy.redditclonebackend.dto.RefreshTokenRequest;
import com.anjaniy.redditclonebackend.dto.RegisterRequest;
import com.anjaniy.redditclonebackend.services.AuthService;
import com.anjaniy.redditclonebackend.services.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Authentication REST Endpoint")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    @Operation(summary = "Endpoint For User Registration.")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {

        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Success", OK);
    }

    @Operation(summary = "Endpoint For User Account Verification.")
    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {

        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @Operation(summary = "Endpoint For User Login.")
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);
    }

    @Operation(summary = "Endpoint For Refresh Token.")
    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @Operation(summary = "Endpoint For User Logout.")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
