package com.travelbuddy.post.controller;

import com.travelbuddy.post.entities.Post;
import com.travelbuddy.post.exception.PostNotExistException;
import com.travelbuddy.post.feign.UserServiceFeignClient;
import com.travelbuddy.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/post")
@CrossOrigin(origins = "https://66bd09f19028ae026b5372d9--courageous-bublanina-dd5958.netlify.app")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getPostsCount", method = RequestMethod.GET)
    public ResponseEntity<Long> getPostsCount() {
        log.info("Request received to get the total number of Posts");
        return new ResponseEntity<>(postService.getPostsCount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/allPosts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Post>> retrieveAllPosts(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        log.info("Request received to get all Post ");
        return ResponseEntity.ok(postService.getAllPosts(page, size));
    }

    @RequestMapping(value = "/getPostsByIds", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostsByIds(@RequestParam List<String> postIds) {
        log.info("Request received to get the posts by postIds");
        return new ResponseEntity<List<Post>>(postService.getPostsByIds(postIds), HttpStatus.OK);
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrievePost(@PathVariable String postId) {
        log.info("Request received to get post for id {}", postId);
        try {
            return ResponseEntity.ok(postService.getPostById(postId));
        } catch (PostNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removePost(@PathVariable String id) {
        log.info("Request received to delete Post with id: {}", id);
        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Post has been Deleted Successfully", HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/createPost",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<?> generatePost(@RequestBody Post post) {
        log.info("Request received to create a Post");
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> renovatePost(@PathVariable String id, @RequestBody Post post) {
        log.info("Request received to update Post with id: {}", id);
        try {
            return new ResponseEntity<>(postService.updatePost(id, post), HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/removeUser/{username}/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUserFromPost(@PathVariable String username, @PathVariable String postId) {
        log.info("Request received to remove user {} from postId {}", username, postId);
        try {
            return new ResponseEntity<>(postService.removeUserFromPost(username, postId), HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addUser/{username}/{postId}", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@PathVariable String username, @PathVariable String postId) {
        log.info("Request received to add user {} to postId {}", username, postId);
        try {
            return new ResponseEntity<>(postService.addUserToPost(username, postId), HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updateStatusToInactive/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusToInactive(@PathVariable String id) {
        log.info("Updating Status to Inactive for id {}", id);
        try {
            return new ResponseEntity<>(postService.updateStatusToInactiveAndMoveToInactiveCollection(id), HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updateStatusToLocked/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusToLocked(@PathVariable String id) {
        log.info("Updating Status to Locked for id {}", id);
        try {
            return new ResponseEntity<>(postService.updateStatusToLocked(id), HttpStatus.OK);
        } catch (PostNotExistException postNotExistException) {
            return new ResponseEntity<>(postNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/test/{username}", method = RequestMethod.GET)
    public String testUsername(@PathVariable String username) {
        try{
//            String url = "https://travelbuddy-user-service-production.up.railway.app/users/gender/{username}";
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, username);
//
//
//            return responseEntity.getBody();
//            return userServiceFeignClient.getGenderFromUsername(username);
           return userServiceFeignClient.getGenderFromUsername(username);
        } catch (Exception e) {
            return "Still the problem in calling another microservice";
        }
    }
}
