package com.ebanxapi.infra.concrete;

import com.ebanxapi.domain.exceptions.UserBalanceNotFound;
import com.ebanxapi.domain.use.cases.ResetUseCase;
import com.ebanxapi.infra.contract.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryRepository implements ResetUseCase,UserBalanceQuery, UserAccountQuery, CreateAccountCommand, UserDepositCommand, UserWithDrawCommand {

    private Map<Integer, Double> usersBalance = new HashMap<>();


    public void reset(){
        this.usersBalance = new HashMap<>();
    }

    @Override
    public Double queryBalance(Integer accountID) {

        if (usersBalance.containsKey(accountID)){
            return usersBalance.get(accountID);
        } else {
            throw new UserBalanceNotFound();
        }
    }

    @Override
    public boolean hasAccount(Integer accountID){
        return usersBalance.containsKey(accountID);
    }

    @Override
    public Double createAccount(Integer accountID, Double value){
        usersBalance.put(accountID,value);
        return usersBalance.get(accountID);
    }

    @Override
    public Double deposit(Integer accountID, Double value){
        double newBalance = 0.0;
        if (usersBalance.containsKey(accountID)){
            newBalance = usersBalance.get(accountID) + value;
            usersBalance.replace(accountID, newBalance);
        }
        return newBalance;
    }

    @Override
    public Double withdraw(Integer accountID, Double value){
        double newBalance = 0.0;
        if (usersBalance.containsKey(accountID)){
            if (usersBalance.get(accountID) >= value)
            newBalance = usersBalance.get(accountID) - value;
            usersBalance.replace(accountID, newBalance);
        }
        return newBalance;
    }

}
