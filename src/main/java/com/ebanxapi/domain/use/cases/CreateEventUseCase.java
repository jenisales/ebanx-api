package com.ebanxapi.domain.use.cases;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;

public interface CreateEventUseCase {

    EventResponse create(Event event);
}
