package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.SubredditDto;
import com.anjaniy.redditclonebackend.exceptions.SubredditNotFoundException;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.repositories.SubredditRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepo subredditRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubreddits() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(mapToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id -" + id));
        return mapToDto(subreddit);
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .description(subreddit.getDescription())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }

    private Subreddit mapToSubreddit(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .user(authService.getCurrentUser())
                .createDate(now()).build();
    }
}
