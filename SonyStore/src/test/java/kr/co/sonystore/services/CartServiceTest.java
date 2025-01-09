package kr.co.sonystore.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Cart;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class CartServiceTest {
    
    @Autowired
    private CartService cartService;


    @Test
    @DisplayName("장바구니에 상품 추가 or 수량변경 테스트")
    void addOrEditItem() {
        Cart input = new Cart();
        input.setCount(1);
        input.setMemberid(24);
        input.setProdid(1);
        input.setColor("white");

        Cart output = null;

        try {
            output = cartService.addOrEditItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("장바구니 상품 수량 변경 테스트")
    void editItem() {
        Cart input = new Cart();
        input.setCartid(8);
        input.setCount(1);

        Cart output = null;

        try {
            output = cartService.editItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test  
    @DisplayName("장바구니 상품 삭제 테스트")
    void deleteItem() {
        Cart input = new Cart();
        input.setCartid(6);

        int output = 0;

        try {
            output = cartService.deleteItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }

 
    @Test
    @DisplayName("장바구니 목록 조회 테스트")
    void getList() {
        Cart input = new Cart();
        input.setMemberid(2);

        List<Cart> output = null;

        try {
            output = cartService.getList(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if (output != null) {
            for ( Cart item : output ) {
                log.debug("output: " + item.toString());
            }
        }
    }


    @Test
    @DisplayName("장바구니 단일 조회 테스트")
    void getItem() {
        Cart input = new Cart();
        input.setCartid(15);

        Cart output = null;

        try {
            output = cartService.getItem(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        if (output != null) {
            log.debug("output: " + output);
        }
    }


    @Test
    @DisplayName("장바구니 다중 삭제 테스트")
    void deleteList() {
        List<Integer> input = List.of(5,8);

        int output = 0;

        try {
            output = cartService.deleteList(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }


    @Test
    @DisplayName("장바구니에 담긴 수량 조회 테스트")
    void getCount() {
        Cart input = new Cart();
        input.setMemberid(19);

        int output = 0;

        try {
            output = cartService.getCount(input);
        } catch (Exception e) {
            log.error("Mapper 구현 에러", e);
        }

        log.debug("output: " + output);
    }

}
