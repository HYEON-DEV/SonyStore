package kr.co.sonystore.schedulers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.models.Payment;
import lombok.extern.slf4j.Slf4j;

/**
 * 스케줄러 샘플 클래스
 * 각 메서드가 정해진 스케줄에 따라 자동 실행된다
 * 프로그램명Application.java 파일의 상단에 @EnableScheduling이 추가돼야 한다
 */
@Slf4j
@Component
@EnableAsync
public class SonyStoreScheduler {
    
    // 업로드 된 파일이 저장될 경로 (application.preperties로부터 읽어온다)
    // @Value("${upload.dir}")
    // private String uploadDir;

    // @Autowired
    // private FileHelper fileHelper;


    public void processNoPayments() throws InterruptedException {
        log.debug("결제대기 목록 정리 시작");
        
        List<Payment> noPayments = null;

        try {
            log.debug("결제대기 목록 조회 및 삭제");
            // noPayments = memberService.processOutMembers();
        } catch (Exception e) {
            log.error("결제대기 목록 조회 및 삭제 실패", e);
            return;
        }

        // if (noPayments == null || noPayments.size()==0) {
        //     log.debug("탈퇴 대상 없음");
        //     return;
        // }

        for ( int i=0; i<noPayments.size(); i++) {      
            Payment p = noPayments.get(i);
            // 사용자가 업로드한 프로필 사진의 실제 경로
        }
    }
}
