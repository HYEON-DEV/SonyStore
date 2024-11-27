package kr.co.sonystore.mappers;

import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        input.setMemberid(2);
        input.setProdid(4);
        input.setColor("블랙");
        
        int output = cartMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getCartid());
    }


    @Test
    @DisplayName("장바구니 수량 증가 테스트")
    void updateCount() {
        Cart input = new Cart();
        input.setCount(1);
        input.setMemberid(2);
        input.setProdid(5);
        // input.setColor("블랙");
        
        int output = cartMapper.updateCount(input);
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("장바구니 목록 조회 테스트")
    void selectList() {
        Cart input = new Cart();
        input.setMemberid(2);
        
        List<Cart> output = cartMapper.selectList(input);
        for ( Cart item : output ) {
            log.debug("output: " + item.toString());
        }
    }


    @Test
    @DisplayName("장바구니 단일행 조회 테스트")
    void selectItem() {
        Cart input = new Cart();
        input.setCartid(15);
        
        Cart output = cartMapper.selectItem(input);
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("장바구니 중복 조회 테스트")
    void selectCount() {
        Cart input = new Cart();
        input.setMemberid(2);
        input.setProdid(5);
        // input.setColor("화이트");
        
        int output = cartMapper.selectCount(input);
        log.debug("output: " + output);
    }


    @Test
    @DisplayName("장바구니 수정 테스트")
    void updateCart() {
        Cart input = new Cart();
        input.setCartid(2);
        input.setCount(2);
        
        int output = cartMapper.update(input); 
        log.debug("output: " + output);
    }   


    @Test
    @DisplayName("장바구니 삭제 테스트")
    void deleteCart() {
        Cart input = new Cart();
        input.setCartid(3);

        int output = cartMapper.delete(input);
        log.debug("output : " + output);
    }


    @Test
    @DisplayName("장바구니 다중 삭제 테스트")
    void deleteCartList() {
       
        List<Integer> inputList = Arrays.asList(11,12);

        int output = cartMapper.deleteList(inputList);
        log.debug("output: " + output); 

    }
}


