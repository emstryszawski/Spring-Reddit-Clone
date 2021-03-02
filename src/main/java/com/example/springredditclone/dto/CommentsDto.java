package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private Long id;
    private Long postId;
    private String text; // comment
    private Instant createdDate;
    private String userName;
}
