package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.CartService;


@RestController
@Tag(name="Cart API", description="장바구니 관련 API")
public class CartRestController {
    
    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;


    /**
     * 장바구니에 상품 추가 (장바구니 버튼 클릭 시)
     */
    @PostMapping("/api/cart/add")
    @Operation(summary="장바구니 추가", description="장바구니에 상품을 추가한다.")
    public Map<String,Object> addCart (
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("prodid") List<Integer> prodid,
        @RequestParam("color") List<String> color,
        @RequestParam("count") List<Integer> count
    ) {
        Cart input = new Cart();

        Cart output = null;

        try {
            for ( int i=0; i<prodid.size(); i++ ) {
                input.setMemberid(memberInfo.getMemberid());
                input.setProdid(prodid.get(i));
                if ( color.get(i) != null && !color.get(i).equals("") && !color.get(i).equals("null") ) {
                    input.setColor(color.get(i));
                }
                input.setCount(count.get(i));
                output = cartService.addOrEditItem(input);
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }
    

    /**
     * 장바구니 수량 변경 
     */
    @PutMapping("/api/cart/edit")
    @Operation(summary="장바구니 수량 변경", description="장바구니의 수량을 변경한다.")
    public Map<String,Object> editCart ( 
        @RequestParam("cartid") int cartid,
        @RequestParam("count") int count
     ) {
        Cart input = new Cart();
        input.setCartid(cartid);
        input.setCount(count);

        Cart output = null;

        try {
            output = cartService.editItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }


    /**
     * 장바구니 상품 삭제 (x버튼 클릭 시)
     */
    @DeleteMapping("/api/cart/deleteItem/{cartid}")
    @Operation(summary="장바구니 상품 단일 삭제", description="장바구니 상품을 단일 삭제한다.")
    public Map<String,Object> deleteItem ( @PathVariable("cartid") int cartid ) {

        Cart input = new Cart();
        input.setCartid(cartid);

        try {
            cartService.deleteItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    /**
     * 장바구니 상품 삭제 (선택삭제 클릭 시)
     */
    @DeleteMapping("/api/cart/deleteList")
    @Operation(summary="장바구니 상품 다중 삭제", description="체크한 상품을 장바구니에서 삭제한다.")
    public Map<String,Object> deleteList (
        @RequestParam("cartidList") List<Integer> cartidList
    ) {
        try {
            cartService.deleteList(cartidList);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

}
