package com.example.social.post;

interface PostService {

    void addPost(String content);

    void addPostByUser(long authorId, String content);
}
