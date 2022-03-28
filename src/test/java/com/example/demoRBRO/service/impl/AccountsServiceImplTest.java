package com.example.demoRBRO.service.impl;

import com.example.demoRBRO.dto.RatesDTO;
import com.example.demoRBRO.dto.ResponseDTO;
import com.example.demoRBRO.repo.AccountRepository;
import com.example.demoRBRO.service.AccountsService;
import com.example.demoRBRO.service.RatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class AccountsServiceImplTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountNotFound(){
        String ibanNotInH2 = "287391823023829302";
        Assertions.assertEquals(null, accountRepository.findAccountByIban(ibanNotInH2));
    }

    @Test
    public void mappingDTOOptimistic(){
        RatesDTO expected = new RatesDTO();
        expected.setEurRate("1");
        String json = "{\"result\":\"success\",\"base_code\":\"EUR\",\"rates\":{\"EUR\":1,\"RON\":1,\"AFN\":95.96}}";
        RatesDTO mapped = new RatesDTO(json);
        Assertions.assertTrue(expected.getEurRate().equals(mapped.getEurRate()));
    }
}
