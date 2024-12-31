package com.crowdseats.event.service;

import com.crowdseats.framework.common.schema.event.EventRequest;

public interface EventService {
    EventRequest createEvent(EventRequest eventRequest);
}
