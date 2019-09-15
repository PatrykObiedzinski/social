package com.example.social.follow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class IdenticalFollowException extends RuntimeException {

    IdenticalFollowException(Long id) {
        super(String.format("Follow with id %s already exists!", id));
    }
}
