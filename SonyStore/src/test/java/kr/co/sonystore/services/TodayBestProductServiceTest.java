package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Today_BestProduct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodayBestProductServiceTest {
    
    @Autowired
    private Today_BestProductService today_BestProductService;

    @Test
    @DisplayName("주간 베스트 상품 조회 테스트")
    void getWeeklyList() {
        List<Today_BestProduct> output = null;

        try {
            output = today_BestProductService.selectWeeklyList(null);
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("월간 베스트 상품 조회 테스트")
    void getMonthlyList() {
        List<Today_BestProduct> output = null;

        try {
            output = today_BestProductService.selectMonthlyList(null);
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }
}
