package com.anjaniy.redditclonebackend.services;

import com.anjaniy.redditclonebackend.dto.SubredditDto;
import com.anjaniy.redditclonebackend.exceptions.SpringRedditException;
import com.anjaniy.redditclonebackend.mappers.SubredditMapper;
import com.anjaniy.redditclonebackend.models.Subreddit;
import com.anjaniy.redditclonebackend.repositories.SubredditRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    @Autowired
    private SubredditRepo subredditRepository;
    @Autowired
    private SubredditMapper subredditMapper;


    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found with id " + id));

        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
