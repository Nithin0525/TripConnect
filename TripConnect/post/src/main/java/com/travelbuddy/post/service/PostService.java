package com.travelbuddy.post.service;

import com.travelbuddy.post.entities.Post;
import com.travelbuddy.post.exception.PostNotExistException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    Post updatePost(String id, Post post) throws PostNotExistException;

    void deletePost(String id) throws PostNotExistException;

    Post getPostById(String id) throws PostNotExistException;

    Page<Post> getAllPosts(int page, int size);

    List<Post> getPostsByIds(List<String> postIds) throws PostNotExistException;

    Post createPost(Post post);

    Boolean removeUserFromPost(String username, String postId);

    Post updateStatusToInactiveAndMoveToInactiveCollection(String postId);

    Post updateStatusToLocked(String postId);

    String addUserToPost(String username, String postId);
    
    Long getPostsCount();

}
