package com.kuber.moneymachine.serivce;

import com.kuber.moneymachine.error.AccountDetailsInvalidException;
import com.kuber.moneymachine.model.service.AccountBalanceResponse;

public interface AccountSummary {


   Double BILLION_CURRENCY_UNIT_VALUE = 1000000000.0;
   Double MILLION_CURRENCY_UNIT_VALUE = 1000000.0;
   Double THOUSAND_CURRENCY_UNIT_VALUE = 1000.0;

   String BILLION_CURRENCY_UNIT = "Billion";
   String MILLION_CURRENCY_UNIT = "Million";
   String THOUSAND_CURRENCY_UNIT = "Thousand";


   // returns current balance value
   Double getAccountBalanceValue(String accountId) throws AccountDetailsInvalidException;

   // Trims balance value with above units
   // 1200000000 = 1.2 Billion
   String getAccountBalancePrettyStr(Double accountBalance) throws AccountDetailsInvalidException;

   AccountBalanceResponse getAccountBalance(String accountId) throws AccountDetailsInvalidException;

}
