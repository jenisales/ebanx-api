package com.ebanxapi.controller;


import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.use.cases.CreateEventUseCase;
import com.ebanxapi.domain.use.cases.ResetUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private CreateEventUseCase create;

    @Autowired
    private ResetUseCase resetUseCase;

    @PostMapping("/event")
    private ResponseEntity<EventResponse> createEvent(@RequestBody Event event){
        var eventResponse = create.create(event);
        return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);
    }

    @PostMapping("/reset")
    private ResponseEntity<String> createEvent(){
        resetUseCase.reset();
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }


}
