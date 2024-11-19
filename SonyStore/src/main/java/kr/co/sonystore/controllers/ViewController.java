package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    /** 상품 상세 페이지 */
    @GetMapping("/product-view/")
    public String view() {
        return "/products/view";
    }
}
