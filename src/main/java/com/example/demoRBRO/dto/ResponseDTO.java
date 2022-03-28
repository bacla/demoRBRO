package com.example.demoRBRO.dto;


import java.math.BigDecimal;


public class ResponseDTO {
    private String iban;
    private BigDecimal euroBallance;
    private String error;

    public ResponseDTO() {
    }

    public ResponseDTO(String iban, BigDecimal euroBallance, String error) {
        this.iban = iban;
        this.euroBallance = euroBallance;
        this.error = error;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getEuroBallance() {
        return euroBallance;
    }

    public void setEuroBallance(BigDecimal euroBallance) {
        this.euroBallance = euroBallance;
    }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }
}
