package com.crowdseats.event.controllers;

import com.crowdseats.event.service.EventService;
import com.crowdseats.framework.common.Response;
import com.crowdseats.framework.common.schema.event.EventRequest;
import com.crowdseats.framework.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @PostMapping
    public Response<?> createEvent(@RequestBody EventRequest eventRequest) {
        EventRequest event = eventService.createEvent(eventRequest);
        return ResponseUtil.prepareResponse(event);
    }
}
