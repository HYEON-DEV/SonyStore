package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    
    @GetMapping("/products")
    public String products() {
        return "/products/category";
    }

    @GetMapping("/products/{type}")
    public String productsByType1(@PathVariable String type) {
        return "/products/category";
    }
}
