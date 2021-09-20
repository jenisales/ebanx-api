package com.ebanxapi.infra.concrete;

import com.ebanxapi.domain.exceptions.UserBalanceNotFound;
import com.ebanxapi.infra.contract.UserBalanceQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryBalanceRepository implements UserBalanceQuery {

    @Override
    public Double queryBalance(Integer accountID) {
        return getBalance(accountID);
    }

    private Double getBalance(Integer account){
        Map<Integer, Double> usersBalance = new HashMap<>();
        usersBalance.put(100, 20.0);

        if (usersBalance.containsKey(account)){
            return usersBalance.get(account);
        } else {
            throw new UserBalanceNotFound();
        }

    }
}
