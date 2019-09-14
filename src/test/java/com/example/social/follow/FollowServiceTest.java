package com.example.social.follow;

import com.example.social.user.User;
import com.example.social.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowServiceTest {

    private static final long MOCKED_FOLLOWER_ID = 1L;
    private static final long MOCKED_FOLLOWING_ID = 2L;

    @Mock
    private FollowDao followDao;

    @Mock
    private UserDao userDao;

    @Captor
    private ArgumentCaptor<Follow> saveArgumentCaptor;

    @InjectMocks
    private FollowService followService;

    @Test
    public void should_add_follow() {
        // given
        given(userDao.findById(anyLong()))
                .willReturn(Optional.of(mockUser(MOCKED_FOLLOWER_ID)))
                .willReturn(Optional.of(mockUser(MOCKED_FOLLOWING_ID)));
        FollowDto followDto = mockFollowDto();

        // when
        followService.addFollow(followDto);

        // then
        verify(followDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(follow -> {
            assertThat(follow.getFollower().getId()).isEqualTo(MOCKED_FOLLOWER_ID);
            assertThat(follow.getFollowing().getId()).isEqualTo(MOCKED_FOLLOWING_ID);
        });
    }

    private FollowDto mockFollowDto() {
        return FollowDto.builder()
                .followerId(MOCKED_FOLLOWER_ID)
                .followingId(MOCKED_FOLLOWING_ID)
                .build();
    }

    private User mockUser(long id) {
        return User.builder()
                .id(id)
                .build();
    }
}
