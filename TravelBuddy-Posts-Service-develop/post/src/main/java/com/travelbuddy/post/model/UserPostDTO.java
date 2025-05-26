package com.travelbuddy.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserPostDTO {
    private String username;
    private List<String> postIds;

}
