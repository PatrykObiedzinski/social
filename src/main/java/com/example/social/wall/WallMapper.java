package com.example.social.wall;

import com.example.social.post.PostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface WallMapper {

    @Mapping(source = "owner.name", target = "ownerName")
    @Mapping(source = "owner.surname", target = "ownerSurname")
    @Mapping(source = "owner.posts", target = "posts")
    WallDto mapToWallDto(Wall wall);
}
