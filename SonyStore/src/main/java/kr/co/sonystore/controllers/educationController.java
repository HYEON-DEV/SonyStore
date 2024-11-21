package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class educationController {
    
    @GetMapping("/education/education_discount")
    public String education() {
        return "education/education_discount";
    }
}
