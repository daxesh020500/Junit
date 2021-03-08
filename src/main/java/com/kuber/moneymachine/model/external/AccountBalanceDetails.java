package com.kuber.moneymachine.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceDetails {

  String accountId;
  Double currentBalance;

}
