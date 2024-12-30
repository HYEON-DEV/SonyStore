package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.TodayMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodayMapperTest {
    
    @Autowired
    private TodayMapper todayMapper;

    @Test
    @DisplayName("집계 결과 insert 테스트")
    void dateMemberTest() {     
        int output = todayMapper.insertNewMemberCount();
        
        log.debug("insert된 데이터 수 " + output);
    }

    @Test
    @DisplayName("주간 매출 집계 테스트")
    void selectWeekListTest() {

        List<TodayMember> output = todayMapper.selectWeekMemberCount();
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("한달간의 주간 매출 집계 테스트")
    void selectMonthListTest() {

        List<TodayMember> output = todayMapper.selectMonthMemberCount();
        log.debug("output: " + output);
    }
}
