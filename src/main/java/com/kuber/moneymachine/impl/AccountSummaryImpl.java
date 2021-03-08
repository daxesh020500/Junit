package com.kuber.moneymachine.impl;

import com.kuber.moneymachine.error.AccountDetailsInvalidException;
import com.kuber.moneymachine.external.AccountDetailsApi;
import com.kuber.moneymachine.model.external.AccountBalanceDetails;
import com.kuber.moneymachine.model.service.AccountBalanceResponse;
import com.kuber.moneymachine.serivce.AccountSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountSummaryImpl implements AccountSummary {

  @Autowired
  private AccountDetailsApi accountDetailsApi;

  @Override
  public Double getAccountBalanceValue(String accountId) throws AccountDetailsInvalidException {
    if(null == accountId){
      throw new AccountDetailsInvalidException();
    }
    else{
      AccountBalanceDetails accountBalanceResponse = accountDetailsApi.getAccountBalance(accountId);
      if(null == accountBalanceResponse) {
        throw new AccountDetailsInvalidException();
      }
      else if(null == accountBalanceResponse.getCurrentBalance()) {
        throw new AccountDetailsInvalidException();
      }
      else if(accountBalanceResponse.getCurrentBalance() <= 0){
        throw new AccountDetailsInvalidException();
      }
      return accountBalanceResponse.getCurrentBalance();
    }

  }

  @Override
  public String getAccountBalancePrettyStr(Double accountBalance) throws AccountDetailsInvalidException {
    if(null == accountBalance) throw  new  AccountDetailsInvalidException();
    if(accountBalance < 0.0) throw  new  AccountDetailsInvalidException();
    String prettyString = null;
    if(accountBalance/BILLION_CURRENCY_UNIT_VALUE >= 1) {
      prettyString =  (accountBalance/BILLION_CURRENCY_UNIT_VALUE) + " " + BILLION_CURRENCY_UNIT;
    }
    else if(accountBalance/MILLION_CURRENCY_UNIT_VALUE >= 1) {
      prettyString = (accountBalance/MILLION_CURRENCY_UNIT_VALUE) + " " + MILLION_CURRENCY_UNIT_VALUE;
    }
    else if(accountBalance/THOUSAND_CURRENCY_UNIT_VALUE >= 1) {
      prettyString = (accountBalance/MILLION_CURRENCY_UNIT_VALUE) + " " + BILLION_CURRENCY_UNIT;
    }
    return (null == prettyString) ? Double.toString(accountBalance) : prettyString;
  }

  @Override
  public AccountBalanceResponse getAccountBalance(String accountId) throws AccountDetailsInvalidException {

    Double accountBalance = getAccountBalanceValue(accountId);
    String prettyString = getAccountBalancePrettyStr(accountBalance);

    AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
    accountBalanceResponse.setAccountId(accountId);
    accountBalanceResponse.setBalance(accountBalance);
    accountBalanceResponse.setPrettyString(prettyString);

    return accountBalanceResponse;

  }
}
