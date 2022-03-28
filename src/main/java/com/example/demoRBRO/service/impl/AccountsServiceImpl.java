package com.example.demoRBRO.service.impl;

import com.example.demoRBRO.dto.RatesDTO;
import com.example.demoRBRO.dto.ResponseDTO;
import com.example.demoRBRO.model.Account;
import com.example.demoRBRO.repo.AccountRepository;
import com.example.demoRBRO.service.AccountsService;
import com.example.demoRBRO.service.RatesService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RatesService ratesService;

    @Override
    public ResponseDTO getEuroAccount(String iban) {
        ResponseDTO responseDTO = new ResponseDTO();
        Account account = accountRepository.findAccountByIban(iban);
        if (account != null) {
            CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
            CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("ratesCircuitBreaker");
            Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, ratesService::getRates);
            String result = Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();
            if (result != null) {
                responseDTO.setIban(iban);
                responseDTO.setEuroBallance(mapEuroBallance(account,result));
            } else {
                throw new RuntimeException("rates external service unavailable ");
            }
        } else {
            throw new RuntimeException("account requested not found");
        }
        return responseDTO;
    }

    private BigDecimal mapEuroBallance(Account account, String result) {
        RatesDTO ratesDTO = new RatesDTO(result);
        BigDecimal eurRate = new BigDecimal(ratesDTO.getEurRate());
        return account.getBalance().divide(eurRate,6, RoundingMode.HALF_DOWN);
    }
}
