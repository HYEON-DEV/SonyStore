package kr.parkjaehan.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.mappers.ProductMapper;
import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductMapperTest {
    
    @Autowired
    private ProductMapper productMapper;

    @Test
    @DisplayName("상품 추가 테스트")
    void insertProduct() {
        Product input = new Product();
        input.setTitle("ILCE-7CM2L");
        input.setDesc("원핸드 컴팩트 풀프레임");
        input.setPrice(3090000);
        input.setType1("카메라");
        input.setType2("렌즈교환식");
        input.setType3("풀프레임");
        input.setDate("20230301");
        input.setDetailimage1("../../detailimage1.png");
        input.setDetailimage2("../../detailimage2.png");
        input.setYoutube("sflsfjowjfyoutube.com");
        input.setDetailgif("../../gif.gif");
        input.setDetailspec("../../spec.png");
        input.setEvent("N");
        input.setColorid(1);
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }
}
