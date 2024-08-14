package com.ohgiraffers.chap07;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String defaultMain() {
        return "main";
    }

    @GetMapping("/main")
    public void main() {}

}
