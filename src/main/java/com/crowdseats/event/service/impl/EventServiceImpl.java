package com.crowdseats.event.service.impl;

import com.crowdseats.event.entities.Event;
import com.crowdseats.event.repositories.EventRepository;
import com.crowdseats.event.service.EventService;
import com.crowdseats.framework.common.Response;
import com.crowdseats.framework.common.feign.clients.PricingServiceClient;
import com.crowdseats.framework.common.feign.clients.ShowTimeServiceClient;
import com.crowdseats.framework.common.schema.event.Category;
import com.crowdseats.framework.common.schema.event.EventObject;
import com.crowdseats.framework.common.schema.event.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ShowTimeServiceClient showtimeServiceClient;
    private final PricingServiceClient pricingServiceClient;

    @Override
    public EventRequest createEvent(EventRequest eventRequest) {
        Event event = new Event();
        mapEventObjectToEvent(eventRequest.getEvent(), event);
        Event savedEvent = eventRepository.save(event);
        EventObject eventObject = mapEventToEventObject(savedEvent);
        eventRequest.setEvent(eventObject);
        return eventRequest;
    }

    /* todo: this method is not used anywhere
     *  will be implemented in the next phase
     */
    @Override
    public EventRequest processEvent(EventRequest eventRequest) {
        EventRequest eventRes;
        eventRes = createEvent(eventRequest);
        Response<?> showTime = showtimeServiceClient.createShowTime(eventRes);
        if (showTime != null && Response.Status.SUCCESS == showTime.getStatus()) {
            eventRes = (EventRequest) showTime.getResult();
        }
        Response<?> pricing = pricingServiceClient.createPricing(eventRes);
        if (pricing != null && Response.Status.SUCCESS == pricing.getStatus()) {
            eventRes = (EventRequest) pricing.getResult();
        }
        return eventRes;
    }

    private EventObject mapEventToEventObject(Event savedEvent) {
        if (savedEvent != null) {
            EventObject eventObject = new EventObject();
            eventObject.setEventId(String.valueOf(savedEvent.getEventId()));
            eventObject.setName(savedEvent.getName());
            eventObject.setDescription(savedEvent.getDescription());
            eventObject.setOrganizer(savedEvent.getOrganizer());
            eventObject.setEventStartDate(savedEvent.getEventStartDate());
            eventObject.setEventEndDate(savedEvent.getEventEndDate());
            eventObject.setCategory(Category.valueOf(savedEvent.getCategory()));
            eventObject.setLocation(savedEvent.getLocation());
            eventObject.setTotalSeats(savedEvent.getTotalSeats());
            return eventObject;
        }
        return null;
    }

    private void mapEventObjectToEvent(EventObject eventObject, Event event) {
        event.setName(eventObject.getName());
        event.setDescription(eventObject.getDescription());
        event.setOrganizer(eventObject.getOrganizer());
        event.setEventStartDate(eventObject.getEventStartDate());
        event.setEventEndDate(eventObject.getEventEndDate());
        event.setCategory(eventObject.getCategory().name());
        event.setLocation(eventObject.getLocation());
        event.setTotalSeats(eventObject.getTotalSeats());
    }
}
