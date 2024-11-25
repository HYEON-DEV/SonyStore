package kr.co.sonystore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.services.CartService;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;
    

    /** 장바구니 페이지 */
    @GetMapping("/cart")
    public String cart( Model model ) {
        
        // 파라미터 추가 @RequestParam("memberid") int memberid
        
        Cart input = new Cart();
        input.setMemberid(2);

        List<Cart> output = null;

        try {
            output = cartService.getList(input);

            for ( Cart cart : output) {
                cart.setFilepath(fileHelper.getUrl(cart.getFilepath()));
            }
            
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("carts", output);

        return "/orders/cart";
    }

}
