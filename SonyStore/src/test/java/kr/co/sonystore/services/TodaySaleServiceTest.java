package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.TodaySale;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class TodaySaleServiceTest {
    
    @Autowired
    private TodaySaleService todaySaleService;

    @Test
    @DisplayName("일별 매출 추가 테스트")
    void addItem() {
        TodaySale output = null;
        try {
            output = todaySaleService.addItem();
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("일별 매출 조회 테스트")
    void getList() {
        List<TodaySale> output = null;
        int day = 7*-1;
        try {
            output = todaySaleService.getList(day);
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }
}
