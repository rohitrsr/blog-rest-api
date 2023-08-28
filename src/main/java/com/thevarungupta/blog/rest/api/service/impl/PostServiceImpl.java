package com.thevarungupta.blog.rest.api.service.impl;

import com.thevarungupta.blog.rest.api.entity.Post;
import com.thevarungupta.blog.rest.api.exception.ResourceNotFoundException;
import com.thevarungupta.blog.rest.api.payload.PostDto;
import com.thevarungupta.blog.rest.api.payload.PostResponse;
import com.thevarungupta.blog.rest.api.repository.PostRepository;
import com.thevarungupta.blog.rest.api.service.PostService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        // create pagable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
         Page<Post> posts =  postRepository.findAll(pageable);

         List<Post> postList = posts.getContent();
         PostResponse postResponse= new PostResponse();
         postResponse.setContent(postList);
         postResponse.setPageNo(posts.getNumber());
         postResponse.setPageSize(posts.getSize());
         postResponse.setTotalElements(posts.getTotalElements());
         postResponse.setTotalPages(posts.getTotalPages());
         postResponse.setLast(posts.isLast());
         return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto updatePost) {
        // get post by id from database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", id));
        // gdt updated data
        post.setTitle(updatePost.getTitle());
        post.setDescription(updatePost.getDescription());
        post.setContent(updatePost.getContent());
        // save the changes
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", id));
        postRepository.deleteById(id);
    }

    // convert Entity to DTO
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
