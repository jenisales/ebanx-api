package com.ebanxapi.domain.strategy.concrete;

import com.ebanxapi.domain.entity.destination.Destination;
import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.entity.event.EventType;
import com.ebanxapi.domain.strategy.EventCommandStrategy;
import com.ebanxapi.infra.concrete.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DepositEventCommand implements EventCommandStrategy {

    @Autowired
    private InMemoryRepository repository;

    @Override
    public boolean canHandle(EventType eventType) {
        return eventType == EventType.DEPOSIT;
    }

    @Override
    public ResponseEntity<EventResponse> commandEvent(Event event) {
        Destination destination = null;

        if (event.getType() == EventType.DEPOSIT){
            double newBalance;
            int accountID = Integer.parseInt(event.getDestination());
            if (repository.hasAccount(accountID)){
                newBalance = repository.deposit(accountID, event.getAmount());
            }else {
                newBalance = repository.createAccount(accountID, event.getAmount());
            }
            destination = new Destination(String.valueOf(accountID), newBalance);
        }

        var eventResponse = EventResponse
                .builder()
                .destination(destination)
                .build();

        return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);
    }
}
