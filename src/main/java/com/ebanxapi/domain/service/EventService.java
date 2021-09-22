package com.ebanxapi.domain.service;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventResponse;
import com.ebanxapi.domain.strategy.EventCommandStrategy;
import com.ebanxapi.domain.strategy.concrete.DepositEventCommand;
import com.ebanxapi.domain.strategy.concrete.TransferEventCommand;
import com.ebanxapi.domain.strategy.concrete.WithdrawEventCommand;
import com.ebanxapi.domain.use.cases.CreateEventUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements CreateEventUseCase {

    @Autowired
    private DepositEventCommand depositEventCommand;

    @Autowired
    private WithdrawEventCommand withdrawEventCommand;

    @Autowired
    private TransferEventCommand transferEventCommand;


    @Override
    public EventResponse create(Event event) {

        List<EventCommandStrategy> eventCommands = List.of(withdrawEventCommand,
                transferEventCommand,
                depositEventCommand);

        for (EventCommandStrategy command: eventCommands) {
            if (command.canHandle(event.getType())){
                return command.commandEvent(event);
            }
        }
        return null;
    }
}
