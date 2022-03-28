package com.example.demoRBRO.controller;

import com.example.demoRBRO.dto.ResponseDTO;
import com.example.demoRBRO.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/rbro")
public class DemoRbroController {

    @Autowired
    private AccountsService accountsService;

//    @GetMapping("/account/{iban}")
//    public ResponseEntity<ResponseDTO> saveConfig(@PathParam("iban") String iban) {
//        return new ResponseEntity<>(new ResponseDTO(null,null,null), HttpStatus.OK);
//    }
    @GetMapping("/account")
    public ResponseEntity<ResponseDTO> getTest(@RequestParam String iban) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            responseDTO = accountsService.getEuroAccount(iban);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e) {
            responseDTO.setError(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }
}
