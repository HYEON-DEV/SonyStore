package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembershipController {
    
    /** 장바구니 페이지 */
    @GetMapping("/membership/benefit")
    public String benefit() {
        return "/membership/benefit";
    }
}
