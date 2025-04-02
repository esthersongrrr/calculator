package com.esther.calculator.service;

import com.esther.calculator.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class SubstractOperation implements OperationStrategy{

    @Override
    public boolean supports(Operation operation) {
        return Operation.SUBTRACT.equals(operation);
    }

    @Override
    public Number apply(Number a, Number b) {
        return a.doubleValue() - b.doubleValue();
    }
}
