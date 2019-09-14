package com.example.social.follow;

class IdenticalUsersException extends RuntimeException {

    IdenticalUsersException() {
        super("The follower and the following cannot be the same person!");
    }
}
