package kr.co.sonystore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sonystore.exceptions.ServiceNoResultException;
import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.MemberService;
import kr.co.sonystore.services.PaylistService;
import kr.co.sonystore.services.PaymentService;

@Controller
public class OrderController {

    @Autowired
    private PaylistService paylistService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private MemberService memberService;


    /** 주문/결제 페이지 */
    @SuppressWarnings("null")
    @GetMapping("/order/sheet")
    public String order_sheet(
        @RequestParam("orderSheetNo") int payid,
        @RequestParam("cartid") List<Integer> cartids,
        Model model
    ) {
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

        Member member = new Member();
        member.setMemberid(outputPayment.getMemberid());
        Member outputMember = null;

        try {
            outputPaylist = paylistService.getList(paylist);
            outputMember = memberService.getMember(member);
            outputDelivery = paymentService.getDlvList(outputPayment);
            
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("payment", outputPayment);
        model.addAttribute("paylists", outputPaylist);
        model.addAttribute("member", outputMember);
        model.addAttribute("dlvs", outputDelivery);
        model.addAttribute("cartids", cartids);

        return "/orders/order_sheet";
    }


    /** 주문 완료 페이지 */
    @GetMapping("/order/complete")
    public String order_complete(
        @RequestParam("orderSheetNo") int payid,
        @RequestParam("cartid") List<Integer> cartids,
        Model model
    ) {
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
