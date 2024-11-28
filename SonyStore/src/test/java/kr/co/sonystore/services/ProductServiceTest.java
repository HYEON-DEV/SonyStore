package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductServiceTest {
    
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품 타입 조회 테스트")
    void aaa() throws Exception{
        Product input = new Product();
        input.setType1("camera");
        List<Product> output = null;

        try {
            output = productService.getItemListByType1(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }
        
        if(output != null) {
            for(Product item : output) {
                log.debug("조회된 데이터: " + item);
            }
        }
    }
}
