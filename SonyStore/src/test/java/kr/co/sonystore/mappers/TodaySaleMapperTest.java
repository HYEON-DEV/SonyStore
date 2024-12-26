package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.TodaySale;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TodaySaleMapperTest {
    
    @Autowired
    private TodaySaleMapper todaySaleMapper;

    @Test
    @DisplayName("일별 매출 집계 추가 테스트")
    void insertTest() {
        int output = todaySaleMapper.insert();
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("일별 매출 집계 조회 테스트")
    void selectListTest() {
        // TodaySale input = new TodaySale();
        List<TodaySale> output = todaySaleMapper.selectList(-7);
        log.debug("output: " + output);
    }
}
