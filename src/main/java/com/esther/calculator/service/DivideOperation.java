package com.esther.calculator.service;

import com.esther.calculator.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class DivideOperation implements OperationStrategy {
    @Override
    public boolean supports(Operation operation) {
        return Operation.DIVIDE.equals(operation);
    }

    @Override
    public Number apply(Number a, Number b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a.doubleValue() / b.doubleValue();
    }
}
