package com.example.social.wall;

import com.example.social.BaseIntegrationTest;
import com.example.social.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WallDaoIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Autowired
    private WallDao wallDao;

    @Test
    public void should_return_a_wall() {
        // given
        Wall wall = mockWall();

        // when
        Optional<Wall> wallFromDatabase = wallDao.findByOwnerId(MOCKED_OWNER_ID);

        // then
        assertThat(wallFromDatabase).hasValue(wall);
    }

    @Test
    @Transactional
    public void should_add_a_wall() {
        // given
        Wall wall = mockWall();

        // when
        Wall wallFromDatabase = wallDao.save(wall);

        // then
        assertThat(wallFromDatabase).isNotNull();
    }

    private Wall mockWall() {
        return Wall.builder()
                .owner(User.builder().build())
                .build();
    }
}
