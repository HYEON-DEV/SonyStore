package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        input.setProdid(1);
        
       colorMapper.insert(input);
    }

    @Test
    @DisplayName("색상 업데이트 테스트")
    void updateColor() {
        Color input = new Color();
        input.setColorid(1);
        input.setColor("white");
        input.setProdid(1);

        colorMapper.update(input);
    }
}
