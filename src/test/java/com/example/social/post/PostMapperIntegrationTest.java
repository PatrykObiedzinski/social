package com.example.social.post;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class PostMapperIntegrationTest extends BaseIntegrationTest {

    private static final String MOCKED_AUTHOR_NAME = "Patryk";
    private static final String MOCKED_AUTHOR_SURNAME = "Obiedziński";
    private static final String MOCKED_MESSAGE = "MOCKED MESSAGE";
    private static final LocalDateTime MOCKED_CREATION_TIME = LocalDateTime.of(2020, 10, 10, 14, 20);

    @Autowired
    private PostMapper postMapper;

    @Test
    public void should_map_post_to_post_dto() {
        // given
        Post post = mockPost();

        // when
        PostDto postDto = postMapper.mapToPostDto(post);

        // then
        assertThat(postDto).satisfies(singlePost -> {
            assertThat(singlePost.getAuthorName()).isEqualTo(MOCKED_AUTHOR_NAME);
            assertThat(singlePost.getAuthorSurname()).isEqualTo(MOCKED_AUTHOR_SURNAME);
            assertThat(singlePost.getContent()).isEqualTo(MOCKED_MESSAGE);
            assertThat(singlePost.getCreationTime()).isEqualTo(MOCKED_CREATION_TIME);
        });
    }

    private Post mockPost() {
        return Post.builder()
                .author(mockUser())
                .content(MOCKED_MESSAGE)
                .creationTime(MOCKED_CREATION_TIME)
                .build();
    }

    private User mockUser() {
        return User.builder()
                .name(MOCKED_AUTHOR_NAME)
                .surname(MOCKED_AUTHOR_SURNAME)
                .follows(emptyList())
                .posts(emptyList())
                .build();
    }
}
