package com.kuber.moneymachine.web;

import com.kuber.moneymachine.error.AccountDetailsInvalidException;
import com.kuber.moneymachine.model.service.AccountBalanceResponse;
import com.kuber.moneymachine.model.web.GenericResponse;
import com.kuber.moneymachine.serivce.AccountSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/account")
public class AccountBalanceController {

  @Autowired
  private AccountSummary accountSummaryService;

  @RequestMapping(path = "/balance/get",method = RequestMethod.GET)
  public GenericResponse<AccountBalanceResponse> getAccountBalance(@RequestParam(name = "accountId") String accountId){
    if(null == accountId) return GenericResponse.getFailureResponse("accountId can't be null");
    try {
      return GenericResponse.getSuccessResponse(accountSummaryService.getAccountBalance(accountId));
    }
    catch (AccountDetailsInvalidException e) {
      return GenericResponse.getFailureResponse(e.getMessage());
    }
    catch (Exception e) {
      return GenericResponse.getFailureResponse("Unexpected Error");
    }
  }
}
