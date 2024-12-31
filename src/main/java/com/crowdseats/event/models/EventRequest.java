package com.crowdseats.event.models;

import lombok.Data;

import java.util.List;

@Data
public class EventRequest {
    private String title;
    private String eventLocation;
    private List<String> showTimes;
    private int totalSeats;
}
