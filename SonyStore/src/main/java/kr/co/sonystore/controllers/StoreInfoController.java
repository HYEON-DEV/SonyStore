package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class StoreInfoController {
    @GetMapping("/store-info")
    public String storeInfo() {
        return "store-info/store_info";
    }
    
}
