package com.travelbuddy.post.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserService",url = "https://travelbuddy-user-service-production.up.railway.app")
public interface UserServiceFeignClient {

    @RequestMapping(value = "/users/gender/{username}", method = RequestMethod.GET)
    public String getGenderFromUsername(@PathVariable String username);

    @PostMapping("/userPosts/{username}/posts/add")
    public String addPostIdToUserBucket(@PathVariable String username, @RequestParam String postId);

    @DeleteMapping("userPosts/{username}/posts/remove")
    public String removePostIdFromUserBucket(@PathVariable String username, @RequestParam String postId);
}
