package com.example.social.follow;

import com.example.social.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Named("followDto")
    default FollowDto mapToFollowDto(Follow follow) {
        Long followerId = follow.getFollower().getId();
        Long followingId = follow.getFollowing().getId();

        return FollowDto.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();
    }

    @Named("followPostDto")
    default FollowPostDto mapToFollowPostDto(Follow follow) {
        List<Post> posts = follow.getFollowing().getPosts();

        return FollowPostDto.builder()
                .posts(posts)
                .build();
    }
}
