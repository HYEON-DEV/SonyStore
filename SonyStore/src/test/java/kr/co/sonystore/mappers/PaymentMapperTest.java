package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaymentMapperTest {
    
    @Autowired
    private PaymentMapper paymentMapper;


    @Test
    @DisplayName("결제내역 추가 테스트")
    void insertPayment() {
        Payment input = new Payment();
        input.setMemberid(2);
        input.setTotalcount(2);
        input.setTotal(100000);
        
        int output = paymentMapper.insert(input);
        
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제내역 단일 조회 테스트")
    void selectItem() {
        Payment input = new Payment();
        input.setPayid(2);
        
        Payment output = paymentMapper.selectItem(input);
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("결제내역 수정 테스트")
    void updatePayment() {
        Payment input = new Payment();
        input.setPayid(2);
        input.setOrdername("홍창기");
        input.setOrderemail("akma@gmail.com");
        input.setOrderphone("01012345678");
        input.setReceivername("신민재");
        input.setReceiverphone("01034563456");
        input.setPostcode("90876");
        input.setAddr1("서울시 강남구 역삼동");
        input.setAddr2("404호");
        input.setRequest(null);
        input.setDlvdate(null);
        input.setPayoption("신용카드");
        
        int output = paymentMapper.update(input);

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("최근 배송지 조회 테스트")
    void selectDlvList() {
        Payment input = new Payment();
        input.setMemberid(2);
        
        List<Payment> output = paymentMapper.selectDlvList(input);
        for(Payment item : output) {
            log.debug("output: " + item);
        }
    }


    @Test
    @DisplayName("미결제상품 조회 테스트")
    void selectNoPayments() {
        List<Payment> output = paymentMapper.selectNoPayments();
        for(Payment item : output) {
            log.debug("output: " + item);
        }
    }


    @Test
    @DisplayName("결제내역 삭제 테스트")
    void deletePayment() {
        int output = paymentMapper.deleteNoPayments();   
        log.debug("output: " + output);
    }
}
