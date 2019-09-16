package com.example.social.timeline;

import com.example.social.follow.FollowMapper;
import com.example.social.follow.FollowPostDto;
import com.example.social.follow.FollowUserMapper;
import com.example.social.post.PostMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static java.util.Collections.singletonList;
import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring", uses = {FollowMapper.class, PostMapper.class})
public interface TimelineMapper {

    @Mapping(source = "owner.name", target = "ownerName")
    @Mapping(source = "owner.surname", target = "ownerSurname")
    @Mapping(source = "owner.follows", target = "follows", qualifiedByName = "followDto")
    @Mapping(source = "owner.follows", target = "posts", qualifiedByName = "followPostDto")
    TimelineDto mapToTimelineDto(Timeline timeline);

    @AfterMapping
    default void afterMapToTimelineDto(@MappingTarget TimelineDto timelineDto, Timeline timeline) {
        if (timelineDto.getPosts().isEmpty()) {
            FollowUserMapper followUserMapper = getMapper(FollowUserMapper.class);
            PostMapper postMapper = getMapper(PostMapper.class);
            FollowPostDto ownerPosts = followUserMapper.mapToFollowPostDto(timeline.getOwner(), postMapper);

            timelineDto.setPosts(singletonList(ownerPosts));
        }
    }
}
