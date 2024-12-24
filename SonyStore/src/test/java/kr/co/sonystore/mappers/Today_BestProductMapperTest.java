package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Today_BestProduct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Today_BestProductMapperTest {
    
    @Autowired
    private Today_BestProductMapper today_BestProductMapper;

    @Test
    @DisplayName("오늘의 베스트 상품 검색 테스트")
    void selectList() {
        List<Today_BestProduct> input = today_BestProductMapper.selectDayList(null);
        for (Today_BestProduct item : input) {
            log.debug("item: " + item.toString());
        }
        
    }

    @Test
    @DisplayName("주간 베스트 상품 검색 테스트")
    void selectWeeklyList() {
        List<Today_BestProduct> input = today_BestProductMapper.selectWeeklyList(null);
        for (Today_BestProduct item : input) {
            log.debug("item: " + item.toString());
        }
    }
    
}
