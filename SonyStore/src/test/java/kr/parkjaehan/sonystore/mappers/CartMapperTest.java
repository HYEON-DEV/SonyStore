package kr.parkjaehan.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.mappers.CartMapper;
import kr.co.sonystore.models.Cart;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CartMapperTest {
    
    @Autowired
    private CartMapper cartMapper;

    @Test
    @DisplayName("장바구니 추가 테스트")
    void insertCart() {
        Cart input = new Cart();
        input.setCount(1);
        input.setMemberid(1);
        input.setProdid(1);
        
        int output = cartMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getCartid());
    }
}
