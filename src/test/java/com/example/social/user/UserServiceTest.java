package com.example.social.user;

import com.example.social.timeline.Timeline;
import com.example.social.timeline.TimelineService;
import com.example.social.wall.Wall;
import com.example.social.wall.WallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Long MOCKED_USER_ID = 1L;

    @Mock
    private UserDao userDao;
    @Mock
    private TimelineService timelineService;
    @Mock
    private WallService wallService;

    @Captor
    private ArgumentCaptor<User> saveArgumentCaptor;

    @InjectMocks
    private UserService userService;

    @Test
    public void should_return_user_by_id() {
        // given
        given(userDao.findById(anyLong())).willReturn(Optional.of(mockUser()));

        // when
        User user = userService.findById(MOCKED_USER_ID);

        // then
        assertThat(user).isEqualToComparingFieldByField(mockUser());
    }

    @Test
    public void should_add_user() {
        // given
        User user = mockUser();

        // when
        userService.addUser(user);

        // then
        verify(userDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).isEqualToComparingFieldByField(mockUser());
    }

    @Test
    public void should_add_mocked_user() {
        // given
        given(timelineService.addTimeline(any())).willReturn(Timeline.builder().build());
        given(wallService.addWall(any())).willReturn(Wall.builder().build());

        // when
        userService.addMockedUser();

        // then
        verify(userDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(user -> {
            assertThat(user.getName()).isEqualTo("Patryk");
            assertThat(user.getSurname()).isEqualTo("Obiedziński");
            assertThat(user.getFollows()).isEmpty();
            assertThat(user.getPosts()).isEmpty();
            assertThat(user.getTimeline()).isNotNull();
            assertThat(user.getWall()).isNotNull();
        });
    }

    private User mockUser() {
        return User.builder()
                .id(1L)
                .build();
    }
}
