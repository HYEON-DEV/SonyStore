package kr.co.sonystore.controllers.restfulapis;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sonystore.models.Product;

public class ProductRestController {
    @GetMapping("/api/category")
    public List<Product> getProductsByCategory(String category) {
        return null;
    }
}
