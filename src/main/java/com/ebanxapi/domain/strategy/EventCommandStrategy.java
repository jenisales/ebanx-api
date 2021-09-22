package com.ebanxapi.domain.strategy;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.entity.event.EventType;
import org.springframework.http.ResponseEntity;

public interface EventCommandStrategy {

    boolean canHandle(EventType eventType);

    ResponseEntity<EventResponse> commandEvent(Event event);

}
