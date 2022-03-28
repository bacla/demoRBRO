package com.example.demoRBRO.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RatesDTO {
    private String eurRate;

    public RatesDTO(String jsonBody) {
        try {
            JsonNode rates = new ObjectMapper().readTree(jsonBody);
            if (rates != null) {
//                eurRate = rates.get("EUR") != null ? rates.get("EUR").asText() : null;
                eurRate = rates.get("rates").get("RON") != null ? rates.get("rates").get("RON").asText() : null;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Rates mapping error: " + e.getMessage(), e);
        }
    }

    public RatesDTO() {

    }


    public String getEurRate() {
        return eurRate;
    }

    public void setEurRate(String eurRate) {
        this.eurRate = eurRate;
    }

}
