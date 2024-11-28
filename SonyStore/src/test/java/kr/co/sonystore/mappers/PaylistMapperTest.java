package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Paylist;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaylistMapperTest {
    
    @Autowired
    private PaylistMapper paylistMapper;


    @Test
    @DisplayName("결제 상품 목록 추가 테스트")
    void insertPaylist() {
        Paylist input = new Paylist();
        input.setPayid(1);
        input.setProdid(4);
        input.setProdthumbnail("/products/camera1/clr1_0.png");
        input.setProdtitle("ZV-E10M2K");
        input.setProdcolor("화이트");
        input.setCount(1);
        input.setProdprice(1490000);
        
        int output = paylistMapper.insert(input);
        
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제 상품 단일 조회 테스트")
    void selectItem() {
        Paylist input = new Paylist();
        input.setPaylistid(1);
        
        Paylist output = paylistMapper.selectItem(input);
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제 상품 목록 조회 테스트")
    void selectList() {
        Paylist input = new Paylist();
        input.setPayid(1);
        
        List<Paylist> output = paylistMapper.selectList(input);
        for (Paylist item : output) {
            log.debug("output: " + item);
        }
    }
}
