package com.example.social.follow;

import com.example.social.BaseIntegrationTest;
import com.example.social.post.Post;
import com.example.social.post.PostMapper;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class FollowUserMapperIntegrationTest extends BaseIntegrationTest {

    private static final String MOCKED_TEXT_MESSAGE = "TEXT_MESSAGE";

    @Autowired
    private FollowUserMapper followUserMapper;
    @Autowired
    private PostMapper postMapper;

    @Test
    public void should_map_user_to_follow_post_dto() {
        // given
        User user = mockUser();

        // when
        FollowPostDto followPostDto = followUserMapper.mapToFollowPostDto(user, postMapper);

        // then
        assertThat(followPostDto).satisfies(followPost ->
                assertThat(followPost.getFollowerPosts()).first().satisfies(first -> {
                    assertThat(first.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE);
                }));
    }

    private User mockUser() {
        return User.builder()
                .posts(mockPosts())
                .build();
    }

    private List<Post> mockPosts() {
        return singletonList(mockPost());
    }

    private Post mockPost() {
        return Post.builder()
                .content(MOCKED_TEXT_MESSAGE)
                .build();
    }
}
