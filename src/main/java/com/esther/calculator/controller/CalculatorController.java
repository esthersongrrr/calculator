package com.esther.calculator.controller;

import com.esther.calculator.dto.ChainInput;
import com.esther.calculator.model.Operation;
import com.esther.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculator;

    @PostMapping("/calculate")
    public Number calculate(@RequestParam Operation operation,
                            @RequestParam Number num1,
                            @RequestParam Number num2) {
        return calculator.calculate(operation, num1, num2);
    }

    @PostMapping("/chain")
    public Number chainCalculate(@RequestParam Number initialValue,
                                 @RequestBody List<ChainInput> requests) {
        return calculator.chainCalculate(initialValue, requests);
    }
}
