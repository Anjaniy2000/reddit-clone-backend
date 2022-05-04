package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.SubredditDto;
import com.anjaniy.redditclonebackend.exceptions.SpringRedditException;
import com.anjaniy.redditclonebackend.mappers.SubredditMapper;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.repositories.SubredditRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepo subredditRepo;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save = subredditRepo.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }


    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubreddits() {
        return subredditRepo
                .findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepo.findById(id)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found with id as" + id));

        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
