package com.example.social.timeline;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TimelineService {

    private final TimelineDao timelineDao;

    Timeline getTimelineByOwnerId(long ownerId) {
        return timelineDao.findTimelineByOwnerId(ownerId)
                .orElseThrow(() -> new TimelineNotFoundException(ownerId));
    }

    @Transactional
    public void addTimeline(Timeline timeline) {
        timelineDao.save(timeline);
    }
}
