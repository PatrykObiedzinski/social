package com.example.social.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDto {

    private String content;
    private String authorName;
    private String authorSurname;
    private LocalDateTime creationTime;

    @Override
    public String toString() {
        return "PostDto{" +
                "content='" + content + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
