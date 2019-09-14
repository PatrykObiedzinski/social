package com.example.social.post;

import com.example.social.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default PostDto mapToPostDto(Post post) {
        User author = post.getAuthor();

        return PostDto.builder()
                .authorName(author.getName())
                .authorSurname(author.getSurname())
                .content(post.getContent())
                .creationTime(post.getCreationTime())
                .build();
    }
}
