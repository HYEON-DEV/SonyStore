package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembersController {
    
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
        

        return "members/my_page";
    }
}
