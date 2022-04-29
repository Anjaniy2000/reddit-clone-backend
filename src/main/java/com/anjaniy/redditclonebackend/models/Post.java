package com.anjaniy.redditclonebackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long postId;
    private String postName;
    private String url;
    private String description;
    private Long voteCount;
    private User user;
    private Instant createDate;
    private Subreddit subreddit;
}
