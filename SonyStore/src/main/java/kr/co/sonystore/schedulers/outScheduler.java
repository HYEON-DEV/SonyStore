package kr.co.sonystore.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class outScheduler {

    @Autowired
    private MemberService memberService;

    // @SCheuled(cron = "0 0 4 * * *") // 매일 새벽 4시에 자동 실행
    @Scheduled(cron = "0 * * * * ?")
    public void processOutMembers() throws InterruptedException {
        log.debug("탈퇴 회원 정리 시작");

        List<Member> outMembers = null;
        try {
            log.debug("탈퇴 회원 조회 및 삭제 시작");
            outMembers = memberService.processOutMembers();
        } catch (Exception e) {
            log.error("탈퇴 회원 조회 및 삭제 실패: " + e);
            return;
        }

        if (outMembers == null || outMembers.size() == 0) {
            log.debug("탈퇴 회원이 없습니다.");
            return;
        }
    }

}
