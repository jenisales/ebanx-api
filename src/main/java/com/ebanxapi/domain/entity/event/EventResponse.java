package com.ebanxapi.domain.entity.event;

import com.ebanxapi.domain.entity.destination.Destination;
import com.ebanxapi.domain.entity.origin.Origin;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "origin", "destination" })
public class EventResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Origin origin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Destination destination;




}
