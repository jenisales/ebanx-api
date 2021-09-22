package com.ebanxapi.domain.strategy.concrete;

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
public class WithdrawEventCommand implements EventCommandStrategy {

    @Autowired
    private InMemoryRepository repository;

    @Override
    public boolean canHandle(EventType eventType) {
        return eventType == EventType.WITHDRAW;
    }

    @Override
    public ResponseEntity<EventResponse> commandEvent(Event event) {
        int accountID = Integer.parseInt(event.getOrigin());
        Double newBalance = 0.0;

        if (repository.hasAccount(accountID)){
            if (repository.queryBalance(accountID) >= event.getAmount()){
                newBalance = repository.withdraw(accountID, event.getAmount());
                var origin = new Origin(String.valueOf(accountID),newBalance);
                var eventResponse= EventResponse
                        .builder()
                        .origin(origin)
                        .build();

                return new ResponseEntity<>(eventResponse,HttpStatus.CREATED);
            }
        }else{
            throw new NonExistingAccount();
        }
        return null;
    }
}
