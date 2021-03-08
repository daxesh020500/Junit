package com.kuber.moneymachine.model.service;

import lombok.Data;

@Data
public class AccountBalanceResponse {

  private String accountId;
  private Double balance;
  private String prettyString;

}
