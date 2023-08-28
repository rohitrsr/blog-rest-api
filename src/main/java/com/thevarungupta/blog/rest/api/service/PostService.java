package com.thevarungupta.blog.rest.api.service;

import com.thevarungupta.blog.rest.api.entity.Post;
import com.thevarungupta.blog.rest.api.payload.PostDto;
import com.thevarungupta.blog.rest.api.payload.PostResponse;
import com.thevarungupta.blog.rest.api.repository.PostRepository;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto newPost);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto updatePost);
    void deletePost(Long id);
}
