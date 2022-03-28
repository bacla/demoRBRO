package com.example.demoRBRO.integration;

import com.example.demoRBRO.repo.AccountRepository;
import com.example.demoRBRO.service.AccountsService;
import com.example.demoRBRO.service.RatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureTestDatabase
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CacheTest {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private AccountRepository accountRepository;
    @SpyBean
    private RatesService ratesService;

    @Test
    public void AccountFoundInDbAndRatesNotCached() {
        String iban1InH2 = "2222";
        String iban2InH2 = "3333";
        Assertions.assertNotNull(accountRepository.findAccountByIban(iban1InH2));
        Assertions.assertNotNull(accountRepository.findAccountByIban(iban2InH2));
//        Mockito.when(ratesService.getRates()).thenReturn("{\"result\":\"success\",\"base_code\":\"EUR\",\"rates\":{\"EUR\":0.2,\"AED\":4.04,\"AFN\":95.96}}");
        accountsService.getEuroAccount(iban1InH2);
        Mockito.verify(ratesService, Mockito.times(1)).getRates();
    }

    @Test
    public void AccountFoundInDbAndRatesCached() {
        String iban1InH2 = "2222";
        String iban2InH2 = "3333";
        Assertions.assertNotNull(accountRepository.findAccountByIban(iban1InH2));
        Assertions.assertNotNull(accountRepository.findAccountByIban(iban2InH2));
        accountsService.getEuroAccount(iban1InH2);
        accountsService.getEuroAccount(iban2InH2);
        Mockito.verify(ratesService, Mockito.times(0)).getRates();
    }
}
