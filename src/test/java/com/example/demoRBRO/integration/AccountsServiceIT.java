package com.example.demoRBRO.integration;

import com.example.demoRBRO.dto.ResponseDTO;
import com.example.demoRBRO.service.AccountsService;
import com.example.demoRBRO.service.RatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureTestDatabase
public class AccountsServiceIT {
    @Autowired
    private AccountsService accountsService;
    @MockBean
    private RatesService ratesService;

    @Test
    public void getConvertedAccountBallance() {
        Mockito.when(ratesService.getRates()).thenReturn("{\"result\":\"success\",\"base_code\":\"EUR\",\"rates\":{\"EUR\":0.2,\"AED\":4.04,\"RON\":4.947729}}");
        ResponseDTO responseDTO = accountsService.getEuroAccount("2222");
        BigDecimal expected = new BigDecimal("2.021129");
        Assertions.assertTrue(expected.compareTo(responseDTO.getEuroBallance()) == 0);
    }


}
