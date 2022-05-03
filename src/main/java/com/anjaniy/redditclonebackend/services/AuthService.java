package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.AuthenticationResponse;
import com.anjaniy.redditclonebackend.dto.LoginRequest;
import com.anjaniy.redditclonebackend.dto.RegisterRequest;
import com.anjaniy.redditclonebackend.exceptions.SpringRedditException;
import com.anjaniy.redditclonebackend.models.NotificationEmail;
import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.models.VerificationToken;
import com.anjaniy.redditclonebackend.repositories.UserRepo;
import com.anjaniy.redditclonebackend.repositories.VerificationTokenRepo;
import com.anjaniy.redditclonebackend.security.JWTProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.anjaniy.redditclonebackend.utilities.Constants.ACTIVATION_EMAIL;

@Service
@AllArgsConstructor
public class AuthService {

//    @Autowired
    private final PasswordEncoder passwordEncoder;

//    @Autowired
    private final UserRepo userRepo;

//    @Autowired
    private final VerificationTokenRepo verificationTokenRepo;

//    @Autowired
    private final MailContentBuilder mailContentBuilder;

//    @Autowired
    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreateDate(Instant.now());
        user.setEnabled(false);

        userRepo.save(user);

        String vToken = generateVerificationToken(user);

        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + vToken);

        mailService.sendMail(new NotificationEmail("Please Activate Your Account", user.getEmail(), message));
    }

    private String generateVerificationToken(User user) {
        String vToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(vToken);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);
        return vToken;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found With Name As - " + username));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());

    }
}
