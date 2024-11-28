package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    
    @GetMapping("/products")
    public String products() {
        return "/products/category";
    }

    @GetMapping("/products/{type}")
    public String productsByType1(@PathVariable("type") String type, Model model) {
        model.addAttribute("type", type);
        return "/products/category";
    }

    @GetMapping("/products/{type}/{type2}")
    public String productsByType2(@PathVariable("type") String type, @PathVariable("type2") String type2, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("type2", type2);
        return "/products/category";
    }


    // @GetMapping("/products/{type}/{categoryKey}")
    // public String productsByCategory(@PathVariable("type") String type, @PathVariable("categoryKey") String categoryKey, Model model) {
    //     model.addAttribute("type", type);
    //     model.addAttribute("categoryKey", categoryKey);
    //     return "/products/category";
    // }
}
