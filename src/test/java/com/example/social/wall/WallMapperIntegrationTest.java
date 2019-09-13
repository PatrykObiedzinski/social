package com.example.social.wall;

import com.example.social.BaseIntegrationTest;
import com.example.social.post.Post;
import com.example.social.user.User;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class WallMapperIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Autowired
    private WallMapper wallMapper;

    @Test
    public void should_map_wall_to_wall_dto() {
        // given
        Wall wall = mockWall();

        // when
        WallDto wallDto = wallMapper.mapToWallDto(wall);

        // then
        assertThat(wallDto).satisfies(singleWall -> {
            assertThat(singleWall.getId()).isEqualTo(wall.getId());
            assertThat(singleWall.getOwner()).isEqualToComparingFieldByField(wall.getOwner());
            assertThat(singleWall.getPosts()).containsAll(wall.getPosts());
        });
    }

    private Wall mockWall() {
        return Wall.builder()
                .id(MOCKED_OWNER_ID)
                .owner(User.builder().build())
                .posts(ImmutableList.of(Post.builder().build()))
                .build();
    }
}
