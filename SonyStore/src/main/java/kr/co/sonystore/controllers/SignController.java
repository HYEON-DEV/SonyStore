package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    
    @GetMapping("/sign/login")
    public String login() {
        return "sign/login";
    }

    @GetMapping("/sign/sign_up_agree")
    public String signUpAgree() {
        return "sign/sign_up_agree";
    }

    @GetMapping("/sign/sign_up_input")
    public String signUpInput() {
        return "sign/sign_up_input";
    }

    @GetMapping("/sign/sign_up")
    public String signUp() {
        return "sign/sign_up";
    }

    @GetMapping("/sign/find_id_pw")
    public String findIdPw() {
        return "sign/find_id_pw";
    }
}
