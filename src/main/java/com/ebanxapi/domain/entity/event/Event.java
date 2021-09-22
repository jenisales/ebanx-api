package com.ebanxapi.domain.entity.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {

    @JsonProperty("type")
    private EventType type;

    @JsonProperty("origin")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String origin;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("destination")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destination;

}
