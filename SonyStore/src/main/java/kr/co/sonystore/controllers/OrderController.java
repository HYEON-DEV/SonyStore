package kr.co.sonystore.controllers;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.CartService;
import kr.co.sonystore.services.PaylistService;
import kr.co.sonystore.services.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private PaylistService paylistService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WebHelper webHelper;

    // @Autowired
    // private MemberService memberService;


    /**
     * 장바구니 페이지를 표시한다
     * @param model - view 에 전달할 데이터
     * @return View 페이지 경로
          * @throws IOException 
          */
         @GetMapping("/cart")
         public String cart( 
             Model model,
             @SessionAttribute(value="memberInfo", required = false) Member memberInfo,
             HttpServletRequest httpRequest,
             HttpServletResponse response
         ) throws IOException {               
        String referer = httpRequest.getHeader("referer");
        if(referer.contains("/order/sheet")) {
           response.sendRedirect("/orders/cart");
           log.debug("이전페이지 주문결제 맞아");
        }

        if(memberInfo == null) {
            return "/orders/cart";
        }
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


    /** 
     * 주문/결제 페이지
     * @throws IOException 
     */
    @SuppressWarnings("null")
    @GetMapping("/order/sheet")
    public String order_sheet(
        @RequestParam("orderSheetNo") int payid,
        @RequestParam(value="cartid", required = false) List<Integer> cartids,
        Model model,
        HttpServletRequest httpRequest,
        HttpServletResponse response
    ) throws IOException {
        String referer = httpRequest.getHeader("referer");

        if ( referer==null || !referer.contains("/cart") || !referer.contains("/product-view") ) {
            webHelper.badRequest("올바르지 않은 접근입니다");
            return null;
        }
        // log.debug("referer: " + referer);

        // if ( referer==null || !referer.contains("cart") || !referer.contains("/product-view")  ) {
        //     webHelper.badRequest("올바르지 않은 접근입니다");
        //     response.sendRedirect(referer!=null ? referer : "/");
        //     return null;
        // }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        Payment payment = new Payment();
        payment.setPayid(payid);

        Payment outputPayment = null;
        try {
            outputPayment = paymentService.getItemNoPaid(payment);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        Paylist paylist = new Paylist();
        paylist.setPayid(payment.getPayid());

        List<Paylist> outputPaylist = null;
        List<Payment> outputDelivery = null;

        // Member member = new Member();
        // member.setMemberid(outputPayment.getMemberid());
        // Member outputMember = null;

        try {
            outputPaylist = paylistService.getList(paylist);
            // outputMember = memberService.getMember(member);
            outputDelivery = paymentService.getDlvList(outputPayment);
        } catch (Exception e) {
            webHelper.serverError(e);
        }   

        model.addAttribute("payment", outputPayment);
        model.addAttribute("paylists", outputPaylist);
        // model.addAttribute("member", outputMember);
        model.addAttribute("dlvs", outputDelivery);
        
        // model.addAttribute("cartids", cartids);

        return "/orders/order_sheet";
    }


    /** 
     * 주문 완료 페이지 
     */
    @GetMapping("/order/complete")
    public String order_complete(
        @RequestParam("orderSheetNo") int payid,
        @RequestParam(value="cartid", required = false) List<Integer> cartids,
        Model model,
        HttpServletRequest httpRequest
    ) {
        String referer = httpRequest.getHeader("referer");
        // log.debug("referer: " + referer);

        if ( referer==null || !referer.contains("/order/sheet") ) {
            webHelper.badRequest("올바르지 않은 접근입니다");
            return "/orders/order_complete";
        }

        Payment input = new Payment();
        input.setPayid(payid);
        
        Payment output = null;
        try {
            output = paymentService.getItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("payment", output);

        return "/orders/order_complete";
    }



    /** 주문/배송 조회 페이지 */
    @GetMapping("/my-page/order-list")
    public String order_list(
        @SessionAttribute(value="memberInfo", required = false) Member memberInfo,
        Model model
    ) {
        if(memberInfo == null) {
            return "/sign/login";
        }

        // 결제완료건 조회
        Payment input = new Payment();
        input.setMemberid(memberInfo.getMemberid());

        int payComplete = 0;
        try {
            payComplete = paymentService.getCountPayComplete(input);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("payComplete", payComplete);

        return "/orders/order_list";
    }



    /** 주문 상세 조회 페이지 */
    @GetMapping("/my-page/order-detail")
    public String order_detail() {
        return "/orders/order_detail";
    }


}
