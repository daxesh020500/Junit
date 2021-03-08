package com.kuber.moneymachine.impl;

import com.kuber.moneymachine.error.AccountDetailsInvalidException;
import com.kuber.moneymachine.external.AccountDetailsApi;
import com.kuber.moneymachine.model.external.AccountBalanceDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AccountSummaryImplTest {

    @InjectMocks
    private AccountSummaryImpl accountSummary;

    @Mock
    private AccountDetailsApi accountDetailsApi;

    @After
    public void tearDown() throws Exception {
        Mockito.verifyNoMoreInteractions(accountDetailsApi);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAccountBalanceValueSuccess() throws AccountDetailsInvalidException {
        Mockito.when(accountDetailsApi.getAccountBalance("abc"))
                .thenReturn(
                         new AccountBalanceDetails("abc",2220.2)
                );
        assertEquals((Double)2220.2,accountSummary.getAccountBalanceValue("abc"));
        Mockito.verify(accountDetailsApi).getAccountBalance("abc");
    }

    @Test(expected = AccountDetailsInvalidException.class)
    public void getAccountBalanceValueFailure() throws AccountDetailsInvalidException {
        try {
            Mockito.when(accountDetailsApi.getAccountBalance("abc"))
                    .thenReturn(null);
            accountSummary.getAccountBalanceValue("abc");
        } catch (Exception e) {
            throw e;
        } finally {
            Mockito.verify(accountDetailsApi).getAccountBalance("abc");
        }
    }
}
