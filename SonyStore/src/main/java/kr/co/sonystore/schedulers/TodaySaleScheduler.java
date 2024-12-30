package kr.co.sonystore.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.models.TodaySale;
import kr.co.sonystore.services.TodaySaleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class TodaySaleScheduler {

    @Autowired
    private TodaySaleService todaySaleService;
    
    @Scheduled(cron = "0 0 1 * * ?")    // 매일 새벽 1시에 실행  
    // @Scheduled(cron = "0 * * * * ?")
    public void processTodaysales() throws InterruptedException{
        
        TodaySale result = null;

        log.debug("매출 집계 시작");

        try {
            log.debug("전날 매출 집계 추가");
            result = todaySaleService.addItem();
        } catch (Exception e) {
            log.debug("매출 집계 실패", e);
            return;
        }

        if (result == null) {
            log.debug("매출 없음");
            return;
        }
        
    }
}
