package com.example.social.post;

import com.example.social.timeline.Timeline;
import com.example.social.user.User;
import com.example.social.user.UserService;
import com.example.social.utils.TimeUtil;
import com.example.social.wall.Wall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    private static final LocalDateTime MOCKED_CREATION_TIME = LocalDateTime.of(2025, 10, 10, 14, 21);
    private static final String MOCKED_TEXT_MESSAGE = "TEXT MESSAGE";
    private static final long MOCKED_AUTHOR_ID = 1L;

    @Mock
    private PostDao postDao;
    @Mock
    private TimeUtil timeUtil;
    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<Post> saveArgumentCaptor;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void should_add_post() {
        // given
        given(userService.addMockedUser()).willReturn(mockUser());
        given(timeUtil.getCurrentDate()).willReturn(MOCKED_CREATION_TIME);

        // when
        postService.addPost(MOCKED_TEXT_MESSAGE);

        // then
        verify(postDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(singlePost -> {
            assertThat(singlePost.getAuthor()).satisfies(getAuthorConditions());
            assertThat(singlePost.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE);
            assertThat(singlePost.getCreationTime()).isEqualTo(MOCKED_CREATION_TIME);
        });
    }

    @Test
    public void should_add_post_by_concrete_user() {
        // given
        given(timeUtil.getCurrentDate()).willReturn(MOCKED_CREATION_TIME);
        given(userService.findById(anyLong())).willReturn(mockUser());

        // when
        postService.addPostByConcreteUser(MOCKED_AUTHOR_ID, MOCKED_TEXT_MESSAGE);

        // then
        verify(postDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).satisfies(singlePost -> {
            assertThat(singlePost.getAuthor()).satisfies(getAuthorConditions());
            assertThat(singlePost.getContent()).isEqualTo(MOCKED_TEXT_MESSAGE);
            assertThat(singlePost.getCreationTime()).isEqualTo(MOCKED_CREATION_TIME);
        });
    }

    private Consumer<User> getAuthorConditions() {
        return author -> {
            assertThat(author.getName()).isEqualTo("Patryk");
            assertThat(author.getSurname()).isEqualTo("Obiedziński");
            assertThat(author.getPosts()).isNotEmpty();
        };
    }

    private User mockUser() {
        return User.builder()
                .name("Patryk")
                .surname("Obiedziński")
                .posts(new ArrayList<>())
                .timeline(mockTimeline())
                .wall(mockWall())
                .build();
    }

    private Timeline mockTimeline() {
        return Timeline.builder()
                .build();
    }

    private Wall mockWall() {
        return Wall.builder()
                .build();
    }
}
