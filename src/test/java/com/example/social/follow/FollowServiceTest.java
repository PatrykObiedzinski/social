package com.example.social.follow;

import com.example.social.user.User;
import com.example.social.user.UserDao;
import com.example.social.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowServiceTest {

    private static final User MOCKED_FOLLOWER = mockUser(1L);
    private static final User MOCKED_FOLLOWING = mockUser(2L);

    @Mock
    private FollowDao followDao;
    @Mock
    private UserDao userDao;
    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<Follow> saveArgumentCaptor;

    @InjectMocks
    private FollowService followService;

    @Test
    public void should_add_follow() {
        // given
        given(userDao.findById(anyLong()))
                .willReturn(Optional.of(MOCKED_FOLLOWER))
                .willReturn(Optional.of(MOCKED_FOLLOWING));
        given(followDao.save(any())).willReturn(mockFollow());
        given(userService.addUser(any())).willReturn(mockUser(1L));
        FollowDto followDto = mockFollowDto();

        // when
        followService.addFollow(followDto);

        // then
        verify(followDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(follow -> {
            assertThat(follow.getFollower().getId()).isEqualTo(MOCKED_FOLLOWER.getId());
            assertThat(follow.getFollowing().getId()).isEqualTo(MOCKED_FOLLOWING.getId());
        });
    }

    private Follow mockFollow() {
        return Follow.builder()
                .follower(MOCKED_FOLLOWER)
                .following(MOCKED_FOLLOWING)
                .build();
    }

    private FollowDto mockFollowDto() {
        return FollowDto.builder()
                .followerId(MOCKED_FOLLOWER.getId())
                .followingId(MOCKED_FOLLOWING.getId())
                .build();
    }

    private static User mockUser(long id) {
        return User.builder()
                .id(id)
                .follows(new ArrayList<>())
                .build();
    }
}
