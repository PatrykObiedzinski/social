package com.example.social.timeline;

import com.example.social.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TimelineServiceTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Mock
    private TimelineDao timelineDao;

    @InjectMocks
    private TimelineService timelineService;

    @Test
    public void should_return_a_timeline_by_owner_id() {
        // given
        given(timelineDao.findTimelineByOwnerId(anyLong())).willReturn(Optional.of(mockTimeline()));

        // when
        Timeline timeline = timelineService.getTimelineByOwnerId(MOCKED_OWNER_ID);

        // then
        assertThat(timeline).isEqualToComparingFieldByFieldRecursively(mockTimeline());
    }

    @Test
    public void should_throw_an_exception_when_no_timeline_found() {
        // given
        given(timelineDao.findTimelineByOwnerId(anyLong())).willReturn(Optional.empty());

        // when-then
        assertThatThrownBy(() -> timelineService.getTimelineByOwnerId(MOCKED_OWNER_ID))
                .isInstanceOf(TimelineNotFoundException.class);
    }

    @Test
    public void should_add_a_timeline() {
        // given
        Timeline timeline = mockTimeline();

        // when
        timelineService.addTimeline(timeline);

        // then
        verify(timelineDao).save(timeline);
    }

    private Timeline mockTimeline() {
        return Timeline.builder()
                .owner(mockUser())
                .build();
    }

    private User mockUser() {
        return User.builder()
                .id(MOCKED_OWNER_ID)
                .build();
    }
}
