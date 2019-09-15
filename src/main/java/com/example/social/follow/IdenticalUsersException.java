package com.example.social.follow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class IdenticalUsersException extends RuntimeException {

    IdenticalUsersException() {
        super("The follower and the following cannot be the same person!");
    }
}
