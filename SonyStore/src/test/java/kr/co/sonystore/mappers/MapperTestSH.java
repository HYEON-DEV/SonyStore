package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Color;
import kr.co.sonystore.models.Image;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MapperTestSH {
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("상품 추가 테스트 (SH)")
    void insertProductSH() {
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
    @DisplayName("상품 추가 테스트 (JH)")
    void insertProduct() {
        Product input = new Product();
        input.setTitle("ILCE-9M3");
        input.setProddesc("The Power of One Frame");
        input.setPrice(7980000);
        input.setType1("카메라");
        input.setType2("렌즈교환식");
        input.setType3("풀프레임");
        input.setDate("20240101");
        input.setDetailimage1("..");
        input.setDetailimage2("..");
        input.setYoutube("..");
        input.setDetailgif("..");
        input.setDetailspec("..");
        input.setSoldout("N");
        input.setSale("Y");
        input.setEvent("Y");
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }


    @Test
    @DisplayName("상품 단일 조회 테스트 (SH)")
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


    @Test
    @DisplayName("색상 추가 테스트 (JH)")
    void insertColor() {
        Color input = new Color();
        input.setColor("블랙");
        input.setProdid(3);
        
       colorMapper.insert(input);
    }

    
    @Test
    @DisplayName("이미지 추가 테스트 (JH)")
    void insertImage(){
        Image input = new Image();
        input.setFilepath("/products/camera1/clr0_0.png");
        input.setThumbnail("Y");
        input.setProdid(4);
        input.setColorid(3);

        imageMapper.insert(input);
    }


    @Test
    @DisplayName("회원 추가 테스트 (SH)")
    void insertMember() {
        Member input = new Member();
        input.setEmail("chann@gmail.com");
        input.setUserpw("qwe123");
        input.setUsername("임찬규");
        input.setGender("M");
        input.setBirthdate("19921120");
        input.setPhone("01022223333");
        // input.setPostcode("12345");
        // input.setAddr1("서울특별시 잠실동");
        // input.setAddr2("123");
        
        int output = memberMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getMemberid());
    }
}
