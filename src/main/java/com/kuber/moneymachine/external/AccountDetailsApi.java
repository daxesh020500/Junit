package com.kuber.moneymachine.external;

import com.kuber.moneymachine.model.external.AccountBalanceDetails;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(path = "localhost:9090")
public interface AccountDetailsApi {

  @RequestMapping(value = "/account/balance/get", method = RequestMethod.GET)
  AccountBalanceDetails getAccountBalance(@RequestParam(name = "accountId") String accountId);
}
