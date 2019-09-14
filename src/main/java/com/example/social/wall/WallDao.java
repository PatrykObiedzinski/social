package com.example.social.wall;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.social.wall.QWall.wall;

@Repository
public class WallDao extends BaseDaoConfiguration {

    Optional<Wall> findByOwnerId(long ownerId) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(wall)
                .where(wall.owner.id.eq(ownerId))
                .fetchOne());
    }

    @Transactional
    void save(Wall wall) {
        getEntityManager().merge(wall);
    }
}
