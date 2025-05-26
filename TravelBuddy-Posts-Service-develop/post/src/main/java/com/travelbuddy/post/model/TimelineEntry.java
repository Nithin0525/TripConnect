package com.travelbuddy.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineEntry {
    private String title;
    private String date; // instant in LocalDate if required
    private List<String> events;
}
