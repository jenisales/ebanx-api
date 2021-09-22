package com.ebanxapi.controller;

import com.ebanxapi.domain.use.cases.GetBalanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BalanceController {

    @Autowired
    private GetBalanceUseCase getBalance;

    @GetMapping("/balance")
    public Double getBalance(@RequestParam("account_id") Integer accountID){
        return getBalance.getUserBalanceFromID(accountID);
    }


}
