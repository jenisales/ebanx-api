package com.ebanxapi.controller;


import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.use.cases.CreateEventUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private CreateEventUseCase create;

    @PostMapping("/event")
    private EventResponse createEvent(@RequestBody Event event){
        return create.create(event);
    }


}
