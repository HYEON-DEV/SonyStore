package kr.co.sonystore.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.services.Today_BestProductService;
import kr.co.sonystore.models.Today_BestProduct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class Today_BestProductScheduler {
    
    @Autowired
    private Today_BestProductService today_bestProductService;

    // @Scheduled(cron = "0 0 0 * 12 ?")
    // @Scheduled(cron = "0 */1 * * * ?")
    public void scheduleInsert() {
        log.debug("오늘의 베스트 상품 집계 시작");
        try {
            log.debug("오늘의 베스트 상품 집계");
            Today_BestProduct input = new Today_BestProduct();
            today_bestProductService.insert(input);
            
        } catch (Exception e) {
            log.error("오늘의 베스트 상품 집계 실패", e);
            return;
        }
    }
}