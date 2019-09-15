package com.example.social.follow;

import com.example.social.BaseIntegrationTest;
import com.example.social.post.Post;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class FollowMapperIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_FOLLOWER_ID = 1L;
    private static final long MOCKED_FOLLOWING_ID = 2L;
    private static final long MOCKED_POST_ID = 1L;
    private static final String MOCKED_TEXT_MESSAGE = "TEXT_MESSAGE";

    @Autowired
    private FollowMapper followMapper;

    @Test
    public void should_map_follow_to_follow_dto() {
        // given
        Follow follow = mockFollow();

        // when
        FollowDto followDto = followMapper.mapToFollowDto(follow);

        // then
        assertThat(followDto).satisfies(singleFollow -> {
            assertThat(singleFollow.getFollowerId()).isEqualTo(MOCKED_FOLLOWER_ID);
            assertThat(singleFollow.getFollowingId()).isEqualTo(MOCKED_FOLLOWING_ID);
        });
    }

    @Test
    public void should_map_follow_to_follow_post_dto() {
        // given
        Follow follow = mockFollow();

        // when
        FollowPostDto followPostDto = followMapper.mapToFollowPostDto(follow);

        // then
        assertThat(followPostDto.getFollowerPosts()).isNotEmpty();
        assertThat(followPostDto.getFollowingPosts()).isNotEmpty();
        assertThat(followPostDto.getFollowerPosts()).first().satisfies(singlePost ->
                assertThat(singlePost.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE));
        assertThat(followPostDto.getFollowingPosts()).first().satisfies(singlePost ->
                assertThat(singlePost.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE));
    }

    private Follow mockFollow() {
        return Follow.builder()
                .follower(mockUser(MOCKED_FOLLOWER_ID))
                .following(mockUser(MOCKED_FOLLOWING_ID))
                .build();
    }

    private User mockUser(long id) {
        return User.builder()
                .id(id)
                .posts(singletonList(mockPost()))
                .build();
    }

    private Post mockPost() {
        return Post.builder()
                .id(MOCKED_POST_ID)
                .content(MOCKED_TEXT_MESSAGE)
                .build();
    }
}
