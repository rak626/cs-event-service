package com.crowdseats.event.controllers;

import com.crowdseats.event.service.EventService;
import com.crowdseats.framework.common.Response;
import com.crowdseats.framework.common.feign.clients.PricingServiceClient;
import com.crowdseats.framework.common.feign.clients.ShowTimeServiceClient;
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
    private final ShowTimeServiceClient showTimeServiceClient;
    private final PricingServiceClient pricingServiceClient;
    @PostMapping("/create")
    public Response<?> createEvent(@RequestBody EventRequest eventRequest) {
        EventRequest eventResponse = eventService.createEvent(eventRequest);
        return ResponseUtil.prepareResponse(eventResponse);
    }

    @PostMapping("/process")
    public Response<?> processEvent(@RequestBody EventRequest eventRequest) {
        EventRequest eventResponse = eventService.processEvent(eventRequest);
        return ResponseUtil.prepareResponse(eventResponse);
    }
}
