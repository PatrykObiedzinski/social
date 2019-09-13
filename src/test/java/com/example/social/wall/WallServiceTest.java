package com.example.social.wall;

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
public class WallServiceTest {

    private static final long MOCKED_OWNER_ID = 1L;

    @Mock
    private WallDao wallDao;

    @InjectMocks
    private WallService wallService;

    @Test
    public void should_return_a_wall_by_owner_id() {
        // given
        given(wallDao.findWallByOwnerId(anyLong())).willReturn(Optional.of(mockWall()));

        // when
        Wall wall = wallService.getWallByOwnerId(MOCKED_OWNER_ID);

        // then
        assertThat(wall).isEqualToComparingFieldByField(mockWall());
    }

    @Test
    public void should_throw_an_exception_when_no_wall_found() {
        // given
        given(wallDao.findWallByOwnerId(anyLong())).willReturn(Optional.empty());

        // when-then
        assertThatThrownBy(() -> wallService.getWallByOwnerId(MOCKED_OWNER_ID))
                .isInstanceOf(WallNotFoundException.class);
    }

    @Test
    public void should_add_a_wall() {
        // given
        Wall wall = mockWall();

        // when
        wallService.addWall(wall);

        // then
        verify(wallDao).save(wall);
    }

    private Wall mockWall() {
        return Wall.builder()
                .id(MOCKED_OWNER_ID)
                .build();
    }
}
