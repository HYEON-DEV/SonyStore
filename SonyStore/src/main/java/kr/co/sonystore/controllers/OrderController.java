package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.sonystore.models.Payment;

@Controller
public class OrderController {

    
    
    /** 장바구니 페이지 */
    // @GetMapping("/cart")
    // public String cart() {
    //     return "/orders/cart";
    // }

    /** 주문/결제 페이지 */
    @GetMapping("/order/sheet/{payid}")
    public String order_sheet(
        @PathVariable("payid") int payid
    ) {
        Payment input = new Payment();
        input.setPayid(payid);

        


        return "/orders/order_sheet";
    }

    /** 주문 완료 페이지 */
    @GetMapping("/order/complete")
    public String order_complete() {
        return "/orders/order_complete";
    }

    /** 주문/배송 페이지 */
    @GetMapping("/my-page/order-list")
    public String order_list() {
        return "/orders/order_list";
    }

    /** 주문 상세 조회 페이지 */
    @GetMapping("/my-page/order-detail")
    public String order_detail() {
        return "/orders/order_detail";
    }


}
