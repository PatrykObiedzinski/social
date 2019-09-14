package com.example.social.follow;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowMapperIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_FOLLOWER_ID = 1L;
    private static final long MOCKED_FOLLOWING_ID = 2L;

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
            assertThat(singleFollow.getFollowerId()).isEqualTo(1L);
            assertThat(singleFollow.getFollowingId()).isEqualTo(2L);
        });
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
                .build();
    }
}
