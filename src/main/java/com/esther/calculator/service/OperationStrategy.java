package com.esther.calculator.service;

import com.esther.calculator.model.Operation;

public interface OperationStrategy {
    boolean supports(Operation operation);
    Number apply(Number a, Number b);
}
