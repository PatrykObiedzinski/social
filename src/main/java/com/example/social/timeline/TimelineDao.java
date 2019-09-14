package com.example.social.timeline;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.social.timeline.QTimeline.timeline;

@Repository
class TimelineDao extends BaseDaoConfiguration {

    Optional<Timeline> findByOwnerId(long ownerId) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(timeline)
                .where(timeline.owner.id.eq(ownerId))
                .fetchOne());
    }

    @Transactional
    void save(Timeline timeline) {
        getEntityManager().merge(timeline);
    }
}
