package com.example.social.timeline;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class TimelineNotFoundException extends RuntimeException {

    TimelineNotFoundException(long ownerId) {
        super(String.format("Timeline for user with id %s has not been found!", ownerId));
    }
}
