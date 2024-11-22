package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class giftController {
    
    @GetMapping("/gift/gift_proposition")
    public String education() {
        return "gift/gift_proposition";
    }
}
