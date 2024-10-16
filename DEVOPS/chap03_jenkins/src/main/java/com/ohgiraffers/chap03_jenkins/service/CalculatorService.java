package com.ohgiraffers.chap03_jenkins.service;

import com.ohgiraffers.chap03_jenkins.dto.CalculatorDTO;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int plusTwoNumbers(CalculatorDTO calculatorDTO) {
        return calculatorDTO.getNum1() + calculatorDTO.getNum2();
    }
}
