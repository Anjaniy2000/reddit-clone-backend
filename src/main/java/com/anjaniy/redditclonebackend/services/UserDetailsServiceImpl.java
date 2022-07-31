package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.models.User;
import com.anjaniy.redditclonebackend.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Collection;
import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        Optional<User> userOptional = userRepo.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No User " + "Found with username: " + username));

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
