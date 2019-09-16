package com.example.social.timeline;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TimelineDaoIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Autowired
    private TimelineDao timelineDao;

    @Test
    public void should_return_a_timeline() {
        // given
        Timeline timeline = mockTimeline();

        // when
        Optional<Timeline> timelineFromDatabase = timelineDao.findByOwnerId(MOCKED_OWNER_ID);

        // then
        assertThat(timelineFromDatabase).hasValue(timeline);
    }

    @Test
    @Transactional
    public void should_add_a_timeline() {
        // given
        Timeline timeline = mockTimeline();

        // when
        Timeline timelineFromDatabase = timelineDao.save(timeline);

        // then
        assertThat(timelineFromDatabase).isNotNull();
    }

    private Timeline mockTimeline() {
        return Timeline.builder()
                .id(MOCKED_OWNER_ID)
                .owner(User.builder().build())
                .build();
    }
}
