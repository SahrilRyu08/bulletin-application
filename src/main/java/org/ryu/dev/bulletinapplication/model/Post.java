package org.ryu.dev.bulletinapplication.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Post {
    private Long id;
    private String title;
    private String author;
    private String password;
    private String content;
    private long viewCount;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean deleted;
}
