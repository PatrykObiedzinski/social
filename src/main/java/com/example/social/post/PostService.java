package com.example.social.post;

interface PostService {

    void addPost(String content);

    void addPostByConcreteUser(long authorId, String content);
}
