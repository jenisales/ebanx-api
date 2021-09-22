package com.ebanxapi.infra.contract;

public interface UserWithDrawCommand {

    Double withdraw(Integer accountID, Double value);

}
