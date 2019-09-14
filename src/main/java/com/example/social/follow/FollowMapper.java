package com.example.social.follow;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    default FollowDto mapToFollowDto(Follow follow) {
        Long followerId = follow.getFollower().getId();
        Long followingId = follow.getFollowing().getId();

        return FollowDto.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();
    }
}
