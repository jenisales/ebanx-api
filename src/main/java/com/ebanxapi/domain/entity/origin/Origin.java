package com.ebanxapi.domain.entity.origin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({ "id", "balance"})
public class Origin {

    private String id;

    private Double balance;

}
