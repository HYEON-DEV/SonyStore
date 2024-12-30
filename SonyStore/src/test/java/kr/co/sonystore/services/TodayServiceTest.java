package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.TodayMember;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class TodayServiceTest {
    
    @Autowired
    private TodayService todayService;

    @Test
    @DisplayName("신규 회원 조회 테스트")
    void getList() {
        int rows = 0;

        try {
            rows = todayService.newMemberCount();
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("rows: " + rows);
    }

    @Test
    @DisplayName("주간 신규 회원 조회 테스트")
    void getWeekList() {
        List<TodayMember> output = null;

        try {
            output = todayService.selectWeekMemberCount();
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("월간 신규 회원 조회 테스트")
    void getMonthList() {
        List<TodayMember> output = null;

        try {
            output = todayService.selectMonthMemberCount();
        } catch (Exception e) {
            log.error("서비스 에러", e);
        }

        log.debug("output: " + output);
    }
}
