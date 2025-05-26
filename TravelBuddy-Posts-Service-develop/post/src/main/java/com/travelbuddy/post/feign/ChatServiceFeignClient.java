package com.travelbuddy.post.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ChatService",url = "https://travelbuddy-chat-service-production-af8a.up.railway.app")
public interface ChatServiceFeignClient {

    @PostMapping("/createRoom/{postId}")
    public String buildChatRoom(@PathVariable String postId);
}
