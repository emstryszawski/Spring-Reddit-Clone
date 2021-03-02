package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data   // https://projectlombok.org/features/Data
@Entity // Specifies that the class is an entity.
@Builder    // https://projectlombok.org/features/Builder
@AllArgsConstructor // https://projectlombok.org/features/constructor
@NoArgsConstructor  // ^^
public class Post {

    @Id // Specifies the primary key of an entity.
    @GeneratedValue(strategy = IDENTITY) // Provides for the specification of generation strategies for the values of primary keys.
    private Long postId;

    @NotBlank(message = "Post name cannot be empty or null") // The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence.
    private String postName;

    @Nullable
    private String url; // annotated elements can be null under some circumstance.

    @Nullable
    @Lob // field should be persisted as a large object to a database-supported large object type.
    private String description;

    private Integer voteCount = 0;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "subredditId", referencedColumnName = "subredditId")
    private Subreddit subreddit;
}
