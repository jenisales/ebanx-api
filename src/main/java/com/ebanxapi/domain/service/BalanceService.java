package com.ebanxapi.domain.service;

import com.ebanxapi.domain.use.cases.GetBalanceUseCase;
import com.ebanxapi.infra.contract.UserBalanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService implements GetBalanceUseCase {

    @Autowired
    private UserBalanceQuery userBalanceQuery;

    @Override
    public Double getUserBalanceFromID(Integer accountID) {
        return userBalanceQuery.queryBalance(accountID);
    }
}
