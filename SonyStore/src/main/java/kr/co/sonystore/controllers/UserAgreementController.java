package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserAgreementController {
    @GetMapping("/user-agreement")
    public String getMethodName() {
        return "user-agreement/user_agreement";
    }
    
}
