package com.kuber.moneymachine.error;

public class AccountDetailsInvalidException extends Exception {

  public AccountDetailsInvalidException() {
    super("Account details couldn't be retrieved or is corrupted");
  }
}
