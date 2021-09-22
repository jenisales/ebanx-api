package com.ebanxapi.domain.strategy;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.entity.event.EventType;

public interface EventCommandStrategy {

    boolean canHandle(EventType eventType);

    EventResponse commandEvent(Event event);

}
