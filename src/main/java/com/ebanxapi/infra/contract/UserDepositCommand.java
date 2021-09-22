package com.ebanxapi.infra.contract;

public interface UserDepositCommand {

    Double deposit(Integer accountID, Double value);

}
