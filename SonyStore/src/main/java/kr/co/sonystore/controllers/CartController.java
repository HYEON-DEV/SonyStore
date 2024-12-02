package kr.co.sonystore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.CartService;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;
    

    /**
     * 장바구니 페이지를 표시한다
     * @param model - view 에 전달할 데이터
     * @return View 페이지 경로
     */
    @GetMapping("/cart")
    public String cart( 
        Model model,
        @SessionAttribute("memberInfo") Member memberInfo
    ) {
               
        Cart input = new Cart();
        input.setMemberid( memberInfo.getMemberid() );

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


    // @PutMapping("/cart/edit")
    // public void edit (
    //     @RequestParam("cartid") int cartid,
    //     @RequestParam("count") int count
    // ) {
    //     Cart input = new Cart();
    //     input.setCartid(cartid);
    //     input.setCount(count);

    //     try {
    //         cartService.editItem(input);
    //     } catch (Exception e) {
    //         webHelper.serverError(e);
    //     }

    // }

}
