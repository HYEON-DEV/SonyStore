package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SearchMapperTest {
    
    @Autowired
    private SearchMapper searchMapper;

    @Test
    @DisplayName("검색된 개수 테스트")
    void SearchCountTest() {
        Product input = new Product();
        input.setTitle("카메라");
        input.setProddesc("카메라");
        input.setType1("카메라");
        input.setType2("카메라");
        input.setType3("카메라");

        int output = searchMapper.selectCount(input);

        log.debug("카메라로 검색한 결과 = " + output);
    }

    @Test
    @DisplayName("검색한 결과 목록 테스트")
    void SearchListTest() {
        Product input = new Product();
        input.setKeyword("카메라");

        List<Product> output = searchMapper.selectList(input);

        log.debug("카메라로 검색한 결과 = " + output);
    }
}
