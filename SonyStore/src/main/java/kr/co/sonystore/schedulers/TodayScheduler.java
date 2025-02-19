package kr.co.sonystore.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.services.TodayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class TodayScheduler {

    @Autowired
    private TodayService todayService;

    // @Scheduled(cron = "0 0 0 * 12 *")
    public void processInsertNewMemberCount() throws InterruptedException {
        log.debug("신규 회원 집계 시작");

        int todayMember = 0;
        try {
            log.debug("신규 회원 집계 시작");
            todayMember = todayService.newMemberCount();
        } catch (Exception e) {
            log.error("신규 회원 집계 실패: " + e);
            return;
        }

        if (todayMember == 0) {
            log.debug("신규 회원이 없습니다.");
            return;
        }
    }

}
