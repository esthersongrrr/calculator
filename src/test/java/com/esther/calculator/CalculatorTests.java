package com.esther.calculator;

import com.esther.calculator.dto.ChainInput;
import com.esther.calculator.model.Operation;
import com.esther.calculator.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CalculatorTests {
    private CalculatorService calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorService(Arrays.asList(
                new AddOperation(),
                new SubstractOperation(),
                new MultiplyOperation(),
                new DivideOperation()
        ));
    }

    @Test
    public void testAddition() {
        Assertions.assertEquals(5.0, calculator.calculate(Operation.ADD, 2, 3));
    }

    @Test
    public void testSubtract() {
        Assertions.assertEquals(5.0, calculator.calculate(Operation.SUBTRACT, 8, 3));
    }

    @Test
    public void testMultuply() {
        Assertions.assertEquals(6.0, calculator.calculate(Operation.MULTIPLY, 2, 3));
    }

    @Test
    public void testDivide() {
        Assertions.assertEquals(5.0, calculator.calculate(Operation.DIVIDE, 10, 2));
    }

    @Test
    public void testChaining() {
        Number result = calculator.chainCalculate(5, Arrays.asList(
                new ChainInput(Operation.ADD, 3),
                new ChainInput(Operation.MULTIPLY, 2)
        ));
        Assertions.assertEquals(16.0, result);
    }

    @Test
    public void testDivisionByZero() {
        Assertions.assertThrows(ArithmeticException.class, () ->
                calculator.calculate(Operation.DIVIDE, 5, 0)
        );
    }

    @Test
    public void testUnsupportedOperation() {
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                calculator.calculate(null, 5, 2)
        );
    }
}
