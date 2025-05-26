package com.travelbuddy.post.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.travelbuddy.post.entities.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>, PagingAndSortingRepository<Post, String> {

    List<Post> findAllByIdIn(List<String> postIds);
}
