package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Background;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class BackgroundMapperTest {
    
    @Autowired
    private BackgroundMapper backgroundMapper;

    @Test
    @DisplayName("배경 추가 테스트")
    void insertBackground(){
        Background input = new Background();
        input.setType("compact");
        input.setFilepath("/products/background/bg_compact_camera.jpg");

        backgroundMapper.insert(input);

    }

    @Test
    @DisplayName("배경 수정 테스트")
    void updateBackground(){
        Background input = new Background();
        input.setBgid(1);
        input.setType("camera");
        input.setFilepath("/products/background/bg_cat_camera.jpg");

        backgroundMapper.update(input);
    }
}
