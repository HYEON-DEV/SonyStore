package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.services.CartService;


@RestController
public class CartRestController {
    
    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;


    @GetMapping("/api/cart")
    public Map<String, Object> getList () {

        // 파라미터 추가 @RequestParam("memberid") int memberid

        Cart input = new Cart();
        // input.setMemberid(memberid);
        input.setMemberid(2);

        List<Cart> output = null;

        try {
            output = cartService.getList(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String,Object>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }
}
