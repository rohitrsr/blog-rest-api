package com.thevarungupta.blog.rest.api.payload;

import com.thevarungupta.blog.rest.api.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message =  "post title should be at least 2 character")
    private String title;

    @NotEmpty
    @Size(min = 10, message =  "post description should be at least 10 character")
    private String description;

    @NotEmpty
    private String content;
    private Set<Comment> comments;

}
