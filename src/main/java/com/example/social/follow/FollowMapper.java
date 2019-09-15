package com.example.social.follow;

import com.example.social.post.PostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
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
    @Mapping(source = "follower.posts", target = "followerPosts")
    @Mapping(source = "following.posts", target = "followingPosts")
    FollowPostDto mapToFollowPostDto(Follow follow);
}
