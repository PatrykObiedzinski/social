package com.example.social.follow;

import com.example.social.post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FollowPostDto {

    private List<PostDto> followerPosts;
    private List<PostDto> followingPosts;

    @Override
    public String toString() {
        return "FollowPostDto{" +
                "followerPosts=" + followerPosts +
                ", followingPosts=" + followingPosts +
                '}';
    }
}

