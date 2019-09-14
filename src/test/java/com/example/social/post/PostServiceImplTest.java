package com.example.social.post;

import com.example.social.timeline.TimelineService;
import com.example.social.user.User;
import com.example.social.user.UserService;
import com.example.social.utils.TimeUtil;
import com.example.social.wall.WallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    private static final LocalDateTime MOCKED_CREATION_TIME = LocalDateTime.of(2025, 10, 10, 14, 21);
    private static final String MOCKED_TEXT_MESSAGE = "TEXT MESSAGE";

    @Mock
    private PostDao postDao;
    @Mock
    private TimeUtil timeUtil;
    @Mock
    private UserService userService;
    @Mock
    private TimelineService timelineService;
    @Mock
    private WallService wallService;

    @Captor
    private ArgumentCaptor<Post> saveArgumentCaptor;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void should_add_post() {
        // given
        given(timeUtil.getCurrentDate()).willReturn(MOCKED_CREATION_TIME);
        doNothing().when(userService).addUser(any());
        doNothing().when(timelineService).addTimeline(any());
        doNothing().when(wallService).addWall(any());

        // when
        postService.addPost(MOCKED_TEXT_MESSAGE);

        // then
        verify(postDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(singlePost -> {
            assertThat(singlePost.getAuthor()).isEqualToComparingFieldByField(mockUser());
            assertThat(singlePost.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE);
            assertThat(singlePost.getCreationTime()).isEqualTo(MOCKED_CREATION_TIME);
        });
    }

    private User mockUser() {
        return User.builder()
                .name("Patryk")
                .surname("Obiedziński")
                .build();
    }
}
