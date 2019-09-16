package com.example.social.follow;

import com.example.social.post.PostDto;
import com.example.social.post.PostMapper;
import com.example.social.user.User;
import org.mapstruct.Mapper;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public interface FollowUserMapper {

    default FollowPostDto mapToFollowPostDto(User user, PostMapper postMapper) {
        return FollowPostDto.builder()
                .followerPosts(getPostDtos(user, postMapper))
                .followingPosts(emptyList())
                .build();
    }

    default List<PostDto> getPostDtos(User user, PostMapper postMapper) {
        return user.getPosts().stream()
                .map(postMapper::mapToPostDto)
                .collect(toList());
    }
}
