package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Paylist;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaylistServiceTest {
    
    @Autowired
    private PaylistService paylistService;


    @Test
    @DisplayName("결제내역 추가 테스트")
    void addItem() {
        Paylist input = new Paylist();
        input.setPayid(3);
        input.setProdid(3);
        input.setProdthumbnail("/products/camera4/clr0_0.png");
        input.setProdtitle("ILCE-7CM2L");
        input.setProdcolor("실버");
        input.setCount(1);
        input.setProdprice(3090000);

        Paylist output = null;
        try {
            output = paylistService.addItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }
    

    @Test
    @DisplayName("결제내역 단일 조회 테스트")
    void getItem() {
        Paylist input = new Paylist();
        input.setPaylistid(3);
        
        Paylist output = null;
        try {
            output = paylistService.getItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제내역 목록 조회 테스트")
    void getList() {
        Paylist input = new Paylist();
        input.setPayid(3);
        
        List<Paylist> output = null;
        try {
            output = paylistService.getList(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if( output != null)
        for (Paylist item : output) {
            log.debug("output: " + item);
        }
    }


    @Test
    @DisplayName("미결제상품 삭제 테스트")
    void deleteByNoPayments() {
        Paylist input = new Paylist();
        input.setPayid(3);
        
        int output = 0;

        try {
            output = paylistService.deleteByNoPayments(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("지정 기간 내의 주문 상품 목록 조회")
    void getListByDate() {
        Paylist input = new Paylist();
        input.
        
        List<Paylist> output = null;
        try {
            output = paylistService.getListByDate(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if( output != null)
        for (Paylist item : output) {
            log.debug("output: " + item);
        }
    }

}
