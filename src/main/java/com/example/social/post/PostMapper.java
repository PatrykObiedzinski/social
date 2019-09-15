package com.example.social.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.surname", target = "authorSurname")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "creationTime", target = "creationTime")
    PostDto mapToPostDto(Post post);
}
