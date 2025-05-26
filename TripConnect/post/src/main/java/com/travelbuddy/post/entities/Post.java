package com.travelbuddy.post.entities;

import com.travelbuddy.post.constants.Constants.Status;
import com.travelbuddy.post.model.Count;
import com.travelbuddy.post.model.TimelineEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "travelbuddy.posts")
public class Post {

    @Id
    private String id; // serves as PostId and ChatId
    private String title;
    private String source;
    private String destination;
    private String startDate;
    private String endDate;
    private Count count;
    private List<TimelineEntry> events = new ArrayList<>();
    private Double amount;
    private List<String> users = new ArrayList<>();
    private Status status;
    private String adminName;
    private int days;
    private int nights;
    private LocalDateTime lastModified;
}