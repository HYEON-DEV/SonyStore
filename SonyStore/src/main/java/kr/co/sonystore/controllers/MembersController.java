package kr.co.sonystore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.PaymentService;

@Controller
public class MembersController {
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private WebHelper webHelper;
    
    @GetMapping("/members/member_modify")
    public String memberModify() {
        return "members/member_modify";
    }

    @GetMapping("/members/member_secession")
    public String memberSescession() {
        return "members/member_secession";
    }

    @GetMapping("/members/my_page")
    public String memberMyPage(
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


        return "members/my_page";
    }
}
