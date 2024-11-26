package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Image;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ImageMapperTest {
    
    @Autowired
    private ImageMapper imageMapper;

    @Test
    @DisplayName("이미지 추가 테스트")
    void insertImage(){
        Image input = new Image();
        input.setFilepath("/products/camera1/clr0_0.png");
        input.setThumbnail("Y");
        input.setProdid(5);
        input.setColorid(4);

        imageMapper.insert(input);

    }

    @Test
    @DisplayName("이미지 업데이트 테스트")
    void updateImage(){
        Image input = new Image();
        input.setImgid(1);
        input.setFilepath("/products/camera1/clr0_1.png");
        input.setThumbnail("N");
        input.setProdid(1);
        input.setColorid(1);

        imageMapper.update(input);
    }

    @Test
    @DisplayName("이미지 삭제 테스트")
    void deleteImage(){
        Image input = new Image();
        input.setImgid(1);

        int output = imageMapper.delete(input);

        log.debug("output = " + output);

    }
}
