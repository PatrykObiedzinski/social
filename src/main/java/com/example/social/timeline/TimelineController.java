package com.example.social.timeline;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineService timelineService;
    private final TimelineMapper timelineMapper;

    @GetMapping("/{ownerId}")
    public TimelineDto getTimelineByOwnerId(@PathVariable long ownerId) {
        Timeline timeline = timelineService.getTimelineByOwnerId(ownerId);
        return timelineMapper.mapToTimelineDto(timeline);
    }
}
