package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
