package com.example.social.timeline;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TimelineService {

    private final TimelineDao timelineDao;

    Timeline getTimelineByOwnerId(long ownerId) {
        return timelineDao.findByOwnerId(ownerId)
                .orElseThrow(() -> new TimelineNotFoundException(ownerId));
    }

    @Transactional
    public Timeline addTimeline(Timeline timeline) {
        return timelineDao.save(timeline);
    }
}
