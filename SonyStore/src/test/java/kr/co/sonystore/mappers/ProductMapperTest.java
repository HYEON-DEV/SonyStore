package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        input.setProddesc("원핸드 컴팩트 풀프레임");
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
        input.setSoldout("N");
        input.setSale("N");
        input.setEvent("N");
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }

    @Test
    @DisplayName("상품 업데이트 테스트")
    void updateProduct() {
        Product input = new Product();
        input.setProdid(1); // 업데이트할 상품의 ID를 설정합니다.
        input.setTitle("ILCE-7CM2L Updated");
        input.setProddesc("업데이트된 원핸드 컴팩트 풀프레임");
        input.setPrice(3190000);
        input.setType1("카메라");
        input.setType2("렌즈교환식");
        input.setType3("풀프레임");
        input.setDate("20230302");
        input.setDetailimage1("../../detailimage1_updated.png");
        input.setDetailimage2("../../detailimage2_updated.png");
        input.setYoutube("updated_youtube.com");
        input.setDetailgif("../../gif_updated.gif");
        input.setDetailspec("../../spec_updated.png");
        input.setSoldout("N");
        input.setSale("Y");
        input.setEvent("Y");
        
        int output = productMapper.updateProduct(input);
        
        log.debug("output: " + output);
    }
}