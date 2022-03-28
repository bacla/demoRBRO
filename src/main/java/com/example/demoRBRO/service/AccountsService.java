package com.example.demoRBRO.service;

import com.example.demoRBRO.dto.ResponseDTO;

public interface AccountsService {
    ResponseDTO getEuroAccount(String iban);
}
