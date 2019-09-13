package com.example.social.timeline;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimelineMapper {

    TimelineDto mapToTimelineDto(Timeline timeline);
}
