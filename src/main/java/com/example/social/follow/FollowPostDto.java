package com.example.social.follow;

import com.example.social.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
class FollowPostDto {

    private List<Post> posts;

    @Override
    public String toString() {
        return "FollowPostDto{" +
                "posts=" + posts +
                '}';
    }
}

