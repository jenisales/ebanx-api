package com.ebanxapi.domain.use.cases;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import org.springframework.http.ResponseEntity;

public interface CreateEventUseCase {

    ResponseEntity<EventResponse> create(Event event);
}
