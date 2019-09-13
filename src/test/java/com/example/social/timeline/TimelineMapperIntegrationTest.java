package com.example.social.timeline;

import com.example.social.BaseIntegrationTest;
import com.example.social.follow.Follow;
import com.example.social.post.Post;
import com.example.social.user.User;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
            assertThat(singleTimeline.getId()).isEqualTo(timeline.getId());
            assertThat(singleTimeline.getOwner()).isEqualToComparingFieldByField(timeline.getOwner());
            assertThat(singleTimeline.getFollows()).containsAll(timeline.getFollows());
            assertThat(singleTimeline.getPosts()).containsAll(timeline.getPosts());
        });
    }

    private Timeline mockTimeline() {
        ImmutableList<Follow> mockedFollows = ImmutableList.of(Follow.builder().build());
        ImmutableList<Post> mockedPosts = ImmutableList.of(Post.builder().build());

        return Timeline.builder()
                .id(MOCKED_OWNER_ID)
                .owner(User.builder().build())
                .follows(mockedFollows)
                .posts(mockedPosts)
                .build();
    }
}
