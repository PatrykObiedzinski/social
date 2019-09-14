package com.example.social.timeline;

import com.example.social.follow.FollowMapper;
import com.example.social.post.PostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FollowMapper.class, PostMapper.class})
public interface TimelineMapper {

    @Mapping(source = "owner.name", target = "ownerName")
    @Mapping(source = "owner.surname", target = "ownerSurname")
    @Mapping(source = "owner.follows", target = "follows")
    @Mapping(source = "owner.posts", target = "posts")
    TimelineDto mapToTimelineDto(Timeline timeline);
}
