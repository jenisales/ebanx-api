package com.ebanxapi.domain.strategy.concrete;

import com.ebanxapi.domain.entity.destination.Destination;
import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.entity.event.EventType;
import com.ebanxapi.domain.entity.origin.Origin;
import com.ebanxapi.domain.exceptions.NonExistingAccount;
import com.ebanxapi.domain.strategy.EventCommandStrategy;
import com.ebanxapi.infra.concrete.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransferEventCommand implements EventCommandStrategy {

    @Autowired
    private InMemoryRepository repository;

    @Override
    public boolean canHandle(EventType eventType) {
        return eventType == EventType.TRANSFER;
    }

    @Override
    public ResponseEntity<EventResponse> commandEvent(Event event) {
        int destinationID = Integer.parseInt(event.getDestination());
        int originID = Integer.parseInt(event.getOrigin());

        if (repository.hasAccount(originID)){
            var originBalance = repository.withdraw(originID, event.getAmount());

            var origin = new Origin(String.valueOf(originID), originBalance);
            var destination = new Destination(String.valueOf(destinationID),event.getAmount());

            var eventResponse = EventResponse
                    .builder()
                    .origin(origin)
                    .destination(destination)
                    .build();

            return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);

        }else {
            throw new NonExistingAccount();
        }
    }
}
