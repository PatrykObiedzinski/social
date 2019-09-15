package com.example.social.follow;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowDaoIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_FOLLOW_ID = 1L;
    private static final long MOCKED_FOLLOWER_ID = 1L;
    private static final long MOCKED_FOLLOWING_ID = 1L;

    @Autowired
    private FollowDao followDao;

    @Test
    @Transactional
    public void should_add_follow() {
        // given
        Follow follow = mockFollow();

        // when
        followDao.save(follow);

        // then
        assertThat(followDao.findById(MOCKED_FOLLOW_ID)).isNotNull();
    }

    @Test
    public void should_return_follow() {
        // given
        Follow follow = mockFollow();

        // when
        Optional<Follow> followFromDatabase = followDao.findById(MOCKED_FOLLOW_ID);

        // then
        assertThat(followFromDatabase).hasValue(follow);
    }

    @Test
    public void should_return_follow_by_follower_and_following_id() {
        // given
        Follow follow = mockFollow();

        // when
        Optional<Follow> followFromDatabase =
                followDao.findByFollowerAndFollowingId(MOCKED_FOLLOWER_ID, MOCKED_FOLLOWING_ID);

        // then
        assertThat(followFromDatabase).hasValue(follow);
    }

    private Follow mockFollow() {
        return Follow.builder()
                .id(MOCKED_FOLLOW_ID)
                .follower(User.builder().build())
                .following(User.builder().build())
                .build();
    }
}
