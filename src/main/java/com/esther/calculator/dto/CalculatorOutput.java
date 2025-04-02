package com.esther.calculator.dto;

public class CalculatorOutput {
    private Number result;

    public CalculatorOutput(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }
}
