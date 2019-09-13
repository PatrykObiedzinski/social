package com.example.social.wall;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class WallNotFoundException extends RuntimeException {

    WallNotFoundException(long ownerId) {
        super(String.format("Wall with id %s has not been found!", ownerId));
    }
}
