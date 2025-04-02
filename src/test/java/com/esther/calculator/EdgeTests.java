package com.esther.calculator;

import com.esther.calculator.dto.ChainInput;
import com.esther.calculator.model.Operation;
import com.esther.calculator.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class EdgeTests {
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
    public void testChainingEmptyList() {
        Number result = calculator.chainCalculate(10, Collections.emptyList());
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testChainingWithZero() {
        Number result = calculator.chainCalculate(0, Arrays.asList(
                new ChainInput(Operation.ADD, 0),
                new ChainInput(Operation.SUBTRACT, 0),
                new ChainInput(Operation.MULTIPLY, 10)
        ));
        Assertions.assertEquals(0.0, result);
    }

    @Test
    public void testNegativeNumbers() {
        Number result = calculator.calculate(Operation.ADD, -5, -3);
        Assertions.assertEquals(-8.0, result);
    }

    @Test
    public void testFloatingPointPrecision() {
        Number result = calculator.calculate(Operation.DIVIDE, 1, 3);
        Assertions.assertEquals(1.0 / 3.0, result);
    }

    @Test
    public void testVeryLargeNumbers() {
        double largeNum1 = 1e308;
        double largeNum2 = 1e308;
        Number result = calculator.calculate(Operation.ADD, largeNum1, largeNum2);
        Assertions.assertTrue(Double.isInfinite(result.doubleValue()), "Result should be infinite due to overflow");
    }

    @Test
    public void testMaxDoubleValues() {
        double max = Double.MAX_VALUE;
        Number result = calculator.calculate(Operation.ADD, max, 1);
        Assertions.assertEquals(Double.MAX_VALUE, result.doubleValue());
    }

    @Test
    public void testMinDoubleValues() {
        double min = -Double.MAX_VALUE;
        Number result = calculator.calculate(Operation.SUBTRACT, min, 1);
        Assertions.assertEquals(-Double.MAX_VALUE, result.doubleValue());
    }

    @Test
    public void testPositiveAndNegativeZero() {
        Assertions.assertEquals(0.0, calculator.calculate(Operation.ADD, 0.0, -0.0));
    }

    @Test
    public void testNaNHandling() {
        Number result = calculator.calculate(Operation.ADD, Double.NaN, 5);
        Assertions.assertTrue(Double.isNaN(result.doubleValue()));
    }

    @Test
    public void testInfinityHandling() {
        Number result = calculator.calculate(Operation.ADD, Double.POSITIVE_INFINITY, 1);
        Assertions.assertTrue(Double.isInfinite(result.doubleValue()));
    }

    @Test
    public void testSingleOperationChain() {
        Number result = calculator.chainCalculate(10, Arrays.asList(
                new ChainInput(Operation.MULTIPLY, 2)
        ));
        Assertions.assertEquals(20.0, result);
    }

    @Test
    public void testExtremeValueChain() {
        Number result = calculator.chainCalculate(1e308, Arrays.asList(
                new ChainInput(Operation.MULTIPLY, 2)
        ));
        Assertions.assertTrue(Double.isInfinite(result.doubleValue()));
    }

    @Test
    public void testIdenticalOperands() {
        Number result = calculator.calculate(Operation.MULTIPLY, 3, 3);
        Assertions.assertEquals(9.0, result);
    }

    @Test
    public void testOperationWithZeroOperand() {
        Number result = calculator.calculate(Operation.MULTIPLY, 0, 999);
        Assertions.assertEquals(0.0, result);
    }

    @Test
    public void testNoEffectOperations() {
        Assertions.assertEquals(42.0, calculator.calculate(Operation.ADD, 42, 0));
        Assertions.assertEquals(42.0, calculator.calculate(Operation.MULTIPLY, 42, 1));
    }
}
