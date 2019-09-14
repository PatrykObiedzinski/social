package com.example.social.timeline;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class TimelineMapperIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Autowired
    private TimelineMapper timelineMapper;

    @Test
    public void should_map_timeline_to_timeline_dto() {
        // given
        Timeline timeline = mockTimeline();

        // when
        TimelineDto timelineDto = timelineMapper.mapToTimelineDto(timeline);

        // then
        assertThat(timelineDto).satisfies(singleTimeline -> {
            assertThat(singleTimeline.getOwnerName()).isEqualTo(timeline.getOwner().getName());
            assertThat(singleTimeline.getOwnerSurname()).isEqualTo(timeline.getOwner().getSurname());
            assertThat(singleTimeline.getFollows()).isEmpty();
            assertThat(singleTimeline.getPosts()).isEmpty();
        });
    }

    private Timeline mockTimeline() {
        return Timeline.builder()
                .id(MOCKED_OWNER_ID)
                .owner(mockUser())
                .build();
    }

    private User mockUser() {
        return User.builder()
                .follows(emptyList())
                .posts(emptyList())
                .build();
    }
}
