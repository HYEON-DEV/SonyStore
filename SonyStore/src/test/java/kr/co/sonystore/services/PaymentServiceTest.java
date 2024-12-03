package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Payment;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class PaymentServiceTest {
    
    @Autowired
    private PaymentService paymentService;


    @Test
    @DisplayName("결제내역 추가 테스트")
    void addItem() {
        Payment input = new Payment();
        input.setMemberid(2);
        input.setTotalcount(2);
        input.setTotal(1000000);
        
        Payment output = null;

        try {
            output = paymentService.addItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제내역 수정 테스트")
    void editItem() {
        Payment input = new Payment();  
        input.setPayid(3);
        input.setOrdername("홍창기");
        input.setOrderemail("akma@gmail.com");
        input.setOrderphone("01012345678");
        input.setReceivername("문보경");
        input.setReceiverphone("01034563456");
        input.setPostcode("09453");
        input.setAddr1("서울시 강남구 서초동");
        input.setAddr2("202호");
        input.setRequest(null);
        input.setDlvdate(null);
        input.setPayoption("신용카드");

        Payment output = null;
        try {
            output = paymentService.editItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if(output != null) {
            log.debug("output: " + output); 
        }
    }


    @Test
    @DisplayName("결제내역 단일 조회 테스트")
    void getItem() {
        Payment input = new Payment();
        input.setPayid(3);
        
        Payment output = null;
        try {
            output = paymentService.getItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if(output != null) {
            log.debug("output: " + output);
        }
    }


    @Test
    @DisplayName("최근 배송지 조회 테스트")
    void getDlvList() {
        Payment input = new Payment();
        input.setMemberid(2);
        
        List<Payment> output = null;
        try {
            output = paymentService.getDlvList(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if(output != null) {
            for(Payment item : output) {
                log.debug("output: " + item);
            }
        }
    }


    @Test
    @DisplayName("미결제상품 결제내역 삭제 테스트")
    void deleteNoPayments() {
        List<Payment> output = null;
       try {
            output = paymentService.deleteNoPayments();
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if ( output != null) {
            for(Payment item : output) {
                log.debug("output: " + item);
            }
        }
    }


    @Test
    @DisplayName("지정된 기간내의 주문내역 조회")
    void getPayListByDate () {
        Payment input = new Payment();
        input.setMemberid(19);
        input.setFromdate("2023-12-03");
        input.setTodate("2024-12-03");

        List<Payment> output = null;
        try {
            output = paymentService.getPayListByDate(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if(output != null) {
            for(Payment item : output) {
                log.debug("output: " + item);
            }
        }
    }
    
}
