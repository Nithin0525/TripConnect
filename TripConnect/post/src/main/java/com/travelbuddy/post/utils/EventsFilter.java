package com.travelbuddy.post.utils;

import com.travelbuddy.post.model.TimelineEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EventsFilter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static List<TimelineEntry> filterEvents(String startDateString, String endDateString, List<TimelineEntry>eventsTimeline)
    {
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        return eventsTimeline.stream().filter(timelineEntry -> {
            LocalDate eventDate = LocalDate.parse(timelineEntry.getDate(),formatter);
            return (eventDate.isEqual(startDate) || eventDate.isAfter(startDate)) &&
                    (eventDate.isEqual(endDate) || eventDate.isBefore(endDate));
        }).collect(Collectors.toList());
    }
}
