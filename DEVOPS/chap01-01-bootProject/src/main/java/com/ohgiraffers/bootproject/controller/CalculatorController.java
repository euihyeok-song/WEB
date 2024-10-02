package com.ohgiraffers.bootproject.controller;

import com.ohgiraffers.bootproject.dto.CalculatorDTO;
import com.ohgiraffers.bootproject.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/health")
    public String healthCheck(){
        return "I'm alive";
    }

    /* 설명. queryString으로 값을 보낸것을 받아서 뽑아내기(param 없이 DTO에 선언한 setter와 getter로 받아올 수 있음 */
    @GetMapping("/plus")
    public ResponseEntity<CalculatorDTO> plusTwoNumbers(CalculatorDTO calculatorDTO){

        log.info("plus 핸들러 실행여부 및 값 확인: " + calculatorDTO);     // toString 자동 적용

        int result = calculatorService.plusTwoNumbers(calculatorDTO);

        /* 설명. response body에 담길 CalculatorDTO의 sum에 추가한다. */
        calculatorDTO.setSum(result);

        return ResponseEntity
                .ok()
                .body(calculatorDTO);
    }
}
