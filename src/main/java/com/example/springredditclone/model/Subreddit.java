package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subreddit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long subredditId;

    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = EAGER)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}