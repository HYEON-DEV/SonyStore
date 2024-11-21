package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductMapperTestSH {
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TestMapper testMapper;

    @Test
    @DisplayName("상품 추가 테스트")
    void insertProduct() {
        Product input = new Product();
        input.setTitle("ILCE-7CM2L");
        input.setProddesc("원핸드 컴팩트 풀프레임");
        input.setPrice(3090000);
        input.setType1("카메라");
        input.setType2("렌즈교환식");
        input.setType3("풀프레임");
        input.setDate("20230301");
        input.setDetailimage1("/products/camera1/clr0_0.png");
        input.setDetailimage2("/products/camera1/clr0_0.png");
        input.setYoutube("youtube.com");
        input.setDetailgif("../../gif.gif");
        input.setDetailspec("../../spec.png");
        input.setSoldout("N");
        input.setSale("N");
        input.setEvent("N");
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }

    @Test
    @DisplayName("상품 단일 조회 테스트")
    void selectProduct() {
        Product input = new Product();
        input.setProdid(3); // 조회할 상품의 ID를 설정

        Product output = testMapper.selectItem(input);

        log.debug("output = " + output.toString());
    }


    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProduct() {
        Product input = new Product();
        input.setProdid(2); // 삭제할 상품의 ID를 설정

        int output = productMapper.delete(input);

        log.debug("output = " + output);

    }
}
