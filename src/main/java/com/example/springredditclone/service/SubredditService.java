package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(mapToSubreddit(subredditDto));
        subredditDto.setId(save.getSubredditId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return mapToDto(subreddit);
    }

    private SubredditDto mapToDto (Subreddit subreddit) {
        return SubredditDto.builder()
                .id(subreddit.getSubredditId())
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .numberOfPosts(
                        subreddit.getPosts().isEmpty() ?
                                0 : subreddit.getPosts().size())
                .build();
    }

    private Subreddit mapToSubreddit (SubredditDto subredditDTO) {
        return Subreddit.builder().name("/r/" + subredditDTO.getName())
                .description(subredditDTO.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(Instant.now())
                .build();
    }
}
