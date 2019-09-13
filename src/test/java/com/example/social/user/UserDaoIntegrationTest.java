package com.example.social.user;

import com.example.social.BaseIntegrationTest;
import com.example.social.follow.Follow;
import com.example.social.post.Post;
import com.example.social.timeline.Timeline;
import com.example.social.wall.Wall;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_USER_ID = 1L;

    @Autowired
    private UserDao userDao;

    @Test
    public void should_return_a_user() {
        // given
        User user = mockUser();

        // when
        Optional<User> userFromDatabase = userDao.findUserById(MOCKED_USER_ID);

        // then
        assertThat(userFromDatabase).hasValue(user);
    }

    @Test
    @Transactional
    public void should_add_user() {
        // given
        User user = mockUser();

        // when
        userDao.save(user);

        // then
        assertThat(userDao.findUserById(1)).isNotNull();
    }

    private User mockUser() {
        ImmutableList<Follow> mockedFollows = ImmutableList.of(Follow.builder().build());
        ImmutableList<Post> mockedPosts = ImmutableList.of(Post.builder().build());

        return User.builder()
                .id(MOCKED_USER_ID)
                .name("Patryk")
                .surname("Obiedziński")
                .follows(mockedFollows)
                .posts(mockedPosts)
                .timeline(Timeline.builder().build())
                .wall(Wall.builder().build())
                .build();
    }
}
