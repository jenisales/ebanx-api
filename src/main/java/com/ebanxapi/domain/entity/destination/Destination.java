package com.ebanxapi.domain.entity.destination;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({ "id", "balance"})
public class Destination {

    private String id;

    private Double balance;

}
