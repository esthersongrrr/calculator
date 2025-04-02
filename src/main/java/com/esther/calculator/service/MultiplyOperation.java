package com.esther.calculator.service;

import com.esther.calculator.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class MultiplyOperation implements OperationStrategy {
    @Override
    public boolean supports(Operation operation) {
        return Operation.MULTIPLY.equals(operation);
    }

    @Override
    public Number apply(Number a, Number b) {
        return a.doubleValue() * b.doubleValue();
    }
}
