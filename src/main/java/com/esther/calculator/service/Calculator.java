package com.esther.calculator.service;

import com.esther.calculator.dto.ChainInput;
import com.esther.calculator.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Calculator {
    private final List<OperationStrategy> strategies;

    @Autowired
    public Calculator(List<OperationStrategy> strategies) {
        this.strategies = strategies;
    }

    public Number calculate(Operation operation, Number num1, Number num2) {
        return strategies.stream()
                .filter(s -> s.supports(operation))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Operation not supported: " + operation))
                .apply(num1, num2);
    }

    public Number chainCalculate(Number initialValue, List<ChainInput> requests) {
        Number result = initialValue;
        for (ChainInput req : requests) {
            result = calculate(req.getOperation(), result, req.getValue());
        }
        return result;
    }
}
