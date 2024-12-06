package kr.co.sonystore.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.PaymentService;
import lombok.extern.slf4j.Slf4j;


/**
 * 각 메서드가 정해진 스케줄에 따라 자동 실행된다
 * 프로그램명Application.java 파일의 상단에 @EnableScheduling이 추가돼야 한다
 */
@Slf4j
@Component
@EnableAsync
public class PaymentScheduler {
    
    @Autowired
    private PaymentService paymentService;

    @Scheduled(cron = "0 * * * * ?")    // 매 분 0초에 실행
    // @Scheduled(cron = "0 0 * * * ?")     // 매 시간 0분 0초에 실행
    public void processNoPayments() throws InterruptedException {
        log.debug("결제대기 목록 정리 시작");
        
        List<Payment> noPayments = null;

        try {
            log.debug("미결제 상품 조회 및 삭제");
            noPayments = paymentService.deleteNoPayments();
        } catch (Exception e) {
            log.error("미결제 상품 삭제 처리 실패", e);
            return;
        }

        if( noPayments == null ) {
            log.debug("미결제 상품 내역 없음");
            return;
        }
    }
}
