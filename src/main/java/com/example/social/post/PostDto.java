package com.example.social.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class PostDto {

    private String authorName;
    private String authorSurname;
    private String content;
    private LocalDateTime creationTime;

    @Override
    public String toString() {
        return "PostDto{" +
                "authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                ", content='" + content + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
