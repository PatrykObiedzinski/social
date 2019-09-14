package com.example.social.wall;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyList;
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
            assertThat(singleWall.getOwnerName()).isEqualTo(wall.getOwner().getName());
            assertThat(singleWall.getOwnerSurname()).isEqualTo(wall.getOwner().getSurname());
            assertThat(singleWall.getPosts()).isEmpty();
        });
    }

    private Wall mockWall() {
        return Wall.builder()
                .id(MOCKED_OWNER_ID)
                .owner(mockUser())
                .build();
    }

    private User mockUser() {
        return User.builder()
                .posts(emptyList())
                .build();
    }
}
