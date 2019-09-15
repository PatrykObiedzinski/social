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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        FollowDto followDto = mockFollowDto(MOCKED_FOLLOWER.getId(), MOCKED_FOLLOWING.getId());

        // when
        followService.addFollow(followDto);

        // then
        verify(followDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(follow -> {
            assertThat(follow.getFollower().getId()).isEqualTo(MOCKED_FOLLOWER.getId());
            assertThat(follow.getFollowing().getId()).isEqualTo(MOCKED_FOLLOWING.getId());
        });
    }

    @Test
    public void should_throw_an_exception_when_identical_users() {
        // given
        FollowDto followDto = mockFollowDto(MOCKED_FOLLOWER.getId(), MOCKED_FOLLOWER.getId());

        // when-then
        assertThatThrownBy(() -> followService.addFollow(followDto))
                .isInstanceOf(IdenticalUsersException.class);
    }

    @Test
    public void should_throw_an_exception_when_identical_follows() {
        // given
        given(userDao.findById(anyLong()))
                .willReturn(Optional.of(MOCKED_FOLLOWER))
                .willReturn(Optional.of(MOCKED_FOLLOWING));
        given(followDao.findByFollowerAndFollowingId(any(), any())).willReturn(Optional.of(mockFollow()));
        FollowDto followDto = mockFollowDto(MOCKED_FOLLOWER.getId(), MOCKED_FOLLOWING.getId());

        // when-then
        assertThatThrownBy(() -> followService.addFollow(followDto))
                .isInstanceOf(IdenticalFollowException.class);
    }

    private Follow mockFollow() {
        return Follow.builder()
                .id(1L)
                .follower(MOCKED_FOLLOWER)
                .following(MOCKED_FOLLOWING)
                .build();
    }

    private FollowDto mockFollowDto(Long followerId, Long followingId) {
        return FollowDto.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();
    }

    private static User mockUser(long id) {
        return User.builder()
                .id(id)
                .follows(new ArrayList<>())
                .build();
    }
}
