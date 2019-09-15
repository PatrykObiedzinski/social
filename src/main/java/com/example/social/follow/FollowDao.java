package com.example.social.follow;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.social.follow.QFollow.follow;

@Repository
public class FollowDao extends BaseDaoConfiguration {

    Optional<Follow> findById(long id) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(follow)
                .where(follow.id.eq(id))
                .fetchOne());
    }

    @Transactional
    Follow save(Follow follow) {
        return getEntityManager().merge(follow);
    }
}
