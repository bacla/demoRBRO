package com.example.demoRBRO.util;

public enum Currencies {
    RON(1),
    EUR(2);
    private final int value;

    Currencies(int v){
        this.value = v;
    }
    public int v() {
        return value;
    }
}
