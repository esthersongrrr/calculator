package com.esther.calculator.dto;

import com.esther.calculator.model.Operation;

public class ChainInput {
    private Operation operation;
    private Number value;

    public ChainInput() {
    }

    public ChainInput(Operation operation, Number value) {
        this.operation = operation;
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
