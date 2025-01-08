package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecommendController {
    @GetMapping("/recommend")
    public String recommend() {
        return "/recommend/recommend";
    }
    
}
