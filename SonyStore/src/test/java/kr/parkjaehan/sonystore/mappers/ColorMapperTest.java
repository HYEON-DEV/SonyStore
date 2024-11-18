package kr.parkjaehan.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.mappers.ColorMapper;
import kr.co.sonystore.models.Color;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ColorMapperTest {
    
    @Autowired
    private ColorMapper colorMapper;

    @Test
    @DisplayName("색상 추가 테스트")
    void insertColor() {
        Color input = new Color();
        input.setColor("black");
        
        int output = colorMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getColorid());
    }
}
